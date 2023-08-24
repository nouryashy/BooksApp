package io.android.projectx.remote.features.books.mapper

import io.android.projectx.data.features.books.model.BookEntity
import io.android.projectx.domain.features.books.model.Book
import io.android.projectx.remote.base.mapper.ModelMapper
import javax.inject.Inject

class BooksResponseModelMapper @Inject constructor() :
    ModelMapper<Book, BookEntity> {
    override fun mapFromModel(model: Book): BookEntity {
        return BookEntity(
        model.id,model.authors,model.bookshelves,model.copyright, model.download_count,model.formats,
        model.languages,model.media_type, model.subjects, model.title,model.translators)
    }

}