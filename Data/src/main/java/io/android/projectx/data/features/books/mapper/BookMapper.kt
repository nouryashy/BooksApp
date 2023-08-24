package io.android.projectx.data.features.books.mapper

import io.android.projectx.data.base.mapper.EntityMapper
import io.android.projectx.data.features.books.model.ResultEntity
import io.android.projectx.domain.features.books.model.Result
import javax.inject.Inject

open class BookMapper @Inject constructor() :
    EntityMapper<ResultEntity, Result> {

    override fun mapFromEntity(entity: ResultEntity): Result {
        return Result(
            entity.id,entity.authors,entity.bookshelves,entity.copyright, entity.download_count,entity.formats,
            entity.languages, entity.media_type, entity.subjects, entity.title, entity.translators
        )
    }

    override fun mapToEntity(domain: Result): ResultEntity {
        return ResultEntity(
             domain.id, domain.authors,domain.bookshelves,domain.copyright, domain.download_count,domain.formats,
            domain.languages, domain.media_type, domain.subjects, domain.title, domain.translators
        )
    }

}