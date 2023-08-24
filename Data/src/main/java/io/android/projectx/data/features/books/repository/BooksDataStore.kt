package io.android.projectx.data.features.books.repository

import io.android.projectx.data.features.books.model.BookEntity
import kotlinx.coroutines.flow.Flow

interface BooksDataStore {


   suspend fun getBooks(): Flow<List<BookEntity>>
}