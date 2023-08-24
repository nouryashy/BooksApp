package io.android.projectx.domain.features.books.interactor

import io.android.projectx.domain.features.books.repository.BooksRepository

class GetBooks(private val booksRepository: BooksRepository) {
    suspend operator fun invoke() = booksRepository.getBooks()
}