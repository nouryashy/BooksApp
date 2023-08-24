package io.android.projectx.cache.features.books.mapper

import io.android.projectx.cache.base.mapper.CacheMapper
import io.android.projectx.cache.features.books.model.CachedBook
import io.android.projectx.data.features.books.model.BookEntity
import javax.inject.Inject

class CachedBookMapper @Inject constructor() :
    CacheMapper<CachedBook, BookEntity> {

    override fun mapFromCached(type: CachedBook): BookEntity {
        return BookEntity(
            type.id,type.authors,type.bookshelves,type.copyright, type.download_count,type.formats,
            type.languages, type.media_type, type.subjects, type.title, type.translators
        )
    }

    override fun mapToCached(entity: BookEntity): CachedBook {
        return CachedBook(
            entity.id, entity.authors,entity.bookshelves,entity.copyright, entity.download_count,entity.formats,
            entity.languages, entity.media_type, entity.subjects, entity.title, entity.translators
        )
    }

}