package io.android.projectx.remote.features.books.service

import io.android.projectx.remote.features.books.model.response.BooksResponceModel
import io.android.projectx.remote.features.recipes.model.response.RecipesResponseModel
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface BooksService {
    @GET("books")
    fun getBooks(
    ): Flow<BooksResponceModel>
}