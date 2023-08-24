package io.android.projectx.cache.features.books.mapper

import io.android.projectx.cache.base.mapper.CacheMapper
import io.android.projectx.cache.features.books.model.CachedResultBook
import io.android.projectx.extensions.getDate
import io.android.projectx.extensions.getOffsetDate
import io.android.projectx.cache.features.recipes.model.CachedRecipe
import io.android.projectx.data.features.books.model.ResultEntity
import io.android.projectx.data.features.recipes.model.RecipeEntity
import java.util.*
import javax.inject.Inject

class CachedBookMapper @Inject constructor() :
    CacheMapper<CachedResultBook, ResultEntity> {

    override fun mapFromCached(type: CachedResultBook): ResultEntity {
        return ResultEntity(
            type.id,type.authors,type.bookshelves,type.copyright, type.download_count,type.formats,
            type.languages, type.media_type, type.subjects, type.title, type.translators
        )
    }

    override fun mapToCached(entity: ResultEntity): CachedResultBook {
        return CachedResultBook(
            entity.id, entity.authors,entity.bookshelves,entity.copyright, entity.download_count,entity.formats,
            entity.languages, entity.media_type, entity.subjects, entity.title, entity.translators
        )
    }

}