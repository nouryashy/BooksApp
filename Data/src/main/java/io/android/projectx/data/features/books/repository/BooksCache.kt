package io.android.projectx.data.features.books.repository

import io.android.projectx.data.features.books.model.BookEntity
import kotlinx.coroutines.flow.Flow

interface BooksCache :BooksDataStore{


    suspend fun saveBooks(books: Flow<List<BookEntity>>)

    suspend fun clearBooks()

    suspend fun areBooksCached(): Boolean

    suspend fun isBooksCacheExpired():Flow<Boolean>

    suspend fun setLastBooksTime(lastCache: Long)
}