package io.android.projectx.data.features.books.mapper

import io.android.projectx.data.base.mapper.EntityMapper
import io.android.projectx.data.features.books.model.BookEntity
import io.android.projectx.domain.features.books.model.Book
import javax.inject.Inject

open class BookMapper @Inject constructor() :
    EntityMapper<BookEntity, Book> {

    override fun mapFromEntity(entity: BookEntity): Book {
        return Book(
            entity.id,entity.authors,entity.bookshelves,entity.copyright, entity.download_count,entity.formats,
            entity.languages, entity.media_type, entity.subjects, entity.title, entity.translators
        )
    }

    override fun mapToEntity(domain: Book): BookEntity {
        return BookEntity(
             domain.id, domain.authors,domain.bookshelves,domain.copyright, domain.download_count,domain.formats,
            domain.languages, domain.media_type, domain.subjects, domain.title, domain.translators
        )
    }

}