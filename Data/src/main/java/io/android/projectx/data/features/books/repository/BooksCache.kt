package io.android.projectx.data.features.books.repository

import io.android.projectx.data.features.books.model.ResultEntity
import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface BooksCache :BooksDataStore{


    suspend fun saveBooks(books: Flow<List<ResultEntity>>)

    suspend fun clearBooks()

    suspend fun areBooksCached(): Boolean

    suspend fun isBooksCacheExpired():Flow<Boolean>

    suspend fun setLastBooksTime(lastCache: Long)
}