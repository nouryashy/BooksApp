package io.android.projectx.domain.features.books.repository

import io.android.projectx.domain.features.books.model.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun getBooks(): Flow<List<Book>>
}