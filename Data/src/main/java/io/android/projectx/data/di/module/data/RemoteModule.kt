package io.android.projectx.data.di.module.data

import android.app.Application
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.android.projectx.extensions.getDate
import io.android.projectx.extensions.getOffsetDate
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.android.projectx.data.features.recipes.store.RecipesRemoteDataStore
import io.android.projectx.data.features.restaurants.repository.RestaurantsRemote
import io.android.projectx.data.features.restaurants.store.RestaurantsRemoteDataStore
import io.android.projectx.data.features.usermanagement.repository.UserManagementRemote
import io.android.projectx.data.features.usermanagement.store.UserManagementCacheDataStore
import io.android.projectx.data.features.usermanagement.store.UserManagementRemoteDataStore
import io.android.projectx.remote.base.interceptor.Authenticator
import io.android.projectx.remote.base.interceptor.NetworkInterceptor
import io.android.projectx.remote.base.interceptor.OfflineCacheInterceptor
import io.android.projectx.remote.base.interceptor.RequestHeaders
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module(includes = [ApiModule::class])
abstract class RemoteModule {

    @Module
    companion object {

        /**
         * The order of the interceptors in the OkHttpClient Builder is important.
         */
        @Provides
        @JvmStatic
        fun provideOkHttpClient(
            @Named("certificate") canInjectCertificate: Boolean,
            @Named("certificate.key") keyCertificate: String,
            @Named("certificate.value") valueCertificate: String,
            cache: Cache,
            httpLoggingInterceptor: HttpLoggingInterceptor,
            networkInterceptor: NetworkInterceptor,
            offlineCacheInterceptor: OfflineCacheInterceptor,
            authenticator: Authenticator
        ): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            if (canInjectCertificate)
                httpClient.certificatePinner(
                    CertificatePinner.Builder()
                        .add(keyCertificate, valueCertificate).build()
                )
            else {
                //Create a trust manager that does not validate certificate chains
                val trustAllCerts: Array<TrustManager> = arrayOf(
                    object : X509TrustManager {
                        override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

                        override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

                        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                    }
                )
                //Install the all-trusting trust manager
                val sslContext: SSLContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                //Create an ssl socket factory with our all-trusting manager
                val  sslSocketFactory : SSLSocketFactory = sslContext.socketFactory
                //Register the sslSocketFactory with OkHttpClient builder
                httpClient.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                httpClient.hostnameVerifier(HostnameVerifier { _, _ -> true })
            }
            return httpClient
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor) // only used when network is on
                .addInterceptor(offlineCacheInterceptor)
                .authenticator(authenticator)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }

        /**
         * //Ref. https://stackoverflow.com/a/44478162
         */
        @Provides
        @JvmStatic
        fun provideMoshi(): Moshi = Moshi.Builder()
            .add(DateAdapter())
            //if you have more adapters, add them before this line:
            .add(KotlinJsonAdapterFactory())
            .build()

        @Provides
        @JvmStatic
        fun provideHttpCache(
            @Named("http.cache.parent.directory") cacheDir: File,
            @Named("http.cache.size") httpCacheSize: Long
        ): Cache {
            val httpCacheDirectory = File(cacheDir, "offlineCache")
            return Cache(httpCacheDirectory, httpCacheSize)
        }

        @Provides
        @JvmStatic
        fun provideRequestHeaders(userManagementCache: UserManagementCacheDataStore, application: Application): RequestHeaders =
            RequestHeaders(userManagementCache, application)

        @Provides
        @JvmStatic
        fun provideAuthenticator(
            requestHeaders: RequestHeaders,
            userManagementRemote: dagger.Lazy<UserManagementRemoteDataStore>
        ): Authenticator = Authenticator(requestHeaders, userManagementRemote)

        @Provides
        @JvmStatic
        fun provideNetworkInterceptor(requestHeaders: RequestHeaders): NetworkInterceptor =
            NetworkInterceptor(requestHeaders)

        @Provides
        @JvmStatic
        fun provideOfflineCacheInterceptor(application: Application): OfflineCacheInterceptor =
            OfflineCacheInterceptor(application)

        @Provides
        @JvmStatic
        fun provideLoggingInterceptor(@Named("signing.configs.debug") isDebug: Boolean): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
            return logging
        }

    }

    @Binds
    abstract fun bindUserManagementRemote(userManagementRemoteImpl: UserManagementRemoteDataStore): UserManagementRemote

    @Binds
    abstract fun bindRecipesRemote(recipesRemoteImpl: RecipesRemoteDataStore): RecipesRemote

    @Binds
    abstract fun bindRestaurantsRemote(restaurantsRemoteImpl: RestaurantsRemoteDataStore): RestaurantsRemote
}

class DateAdapter {
    @FromJson
    fun fromJson(json: String): Date? = json.getDate()

    @ToJson
    fun toJson(date: Date) = date.getOffsetDate()
}