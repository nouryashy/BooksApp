package io.android.projectx.data.features.books.store

import io.android.projectx.cache.AppDatabase
import io.android.projectx.cache.features.books.mapper.CachedBookMapper
import io.android.projectx.cache.features.config.model.Config
import io.android.projectx.data.features.books.model.ResultEntity
import io.android.projectx.data.features.books.repository.BooksCache
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BooksCacheDataStore @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: CachedBookMapper
) : BooksCache {


    companion object {
        const val KEY_GET_BOOKS = "key_get_books"
        const val expirationTime = (60 * 1000 * 1).toLong()
    }


    override suspend fun getBooks(): Flow<List<ResultEntity>> {
        return appDatabase.cachedBooksDao().getBooks().map {
            it.map { mapper.mapFromCached(it) }
        }

    }

    override suspend fun clearBooks() {
        appDatabase.cachedBooksDao().deleteBooks()
    }


    override suspend fun saveBooks(books: Flow<List<ResultEntity>>) {
        return appDatabase.cachedBooksDao().insertBooks(books.map { mapper.mapToCached(it) })
    }

    override suspend fun areBooksCached(): Boolean {
        val books = appDatabase.cachedBooksDao().getBooks()
        var anyBooksEmitted = false
        books.collect {
            anyBooksEmitted = true
        }
        return (anyBooksEmitted)
    }

    override suspend fun isBooksCacheExpired(): Flow<Boolean> {
        TODO("Not yet implemented")
    }


    override suspend fun setLastBooksTime(lastCache: Long) {
        return appDatabase.configDao()
            .insertConfig(
                Config(BooksCacheDataStore.KEY_GET_BOOKS, "", lastCacheTime = lastCache)
            )
    }


}