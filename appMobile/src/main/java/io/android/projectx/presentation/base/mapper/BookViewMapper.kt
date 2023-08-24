package io.android.projectx.presentation.base.mapper

import io.android.projectx.domain.features.books.model.Book
import io.android.projectx.presentation.base.model.BookView
import javax.inject.Inject

open class BookViewMapper @Inject constructor() : Mapper<BookView, Book> {

    override fun mapToView(type: Book): BookView {
        return BookView(
        type.id,type.authors,type.bookshelves,type.copyright, type.download_count,type.formats,
        type.languages, type.media_type, type.subjects, type.title, type.translators
        )
    }
}