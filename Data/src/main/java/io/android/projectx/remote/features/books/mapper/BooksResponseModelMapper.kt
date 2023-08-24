package io.android.projectx.remote.features.books.mapper

import io.android.projectx.data.features.books.model.ResultEntity
import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.domain.features.books.model.Result
import io.android.projectx.remote.base.mapper.ModelMapper
import io.android.projectx.remote.features.recipes.model.RecipeModel
import java.util.*
import javax.inject.Inject

class BooksResponseModelMapper @Inject constructor() :
    ModelMapper<Result, ResultEntity> {
    override fun mapFromModel(model: Result): ResultEntity {
        return ResultEntity(
        model.id,model.authors,model.bookshelves,model.copyright, model.download_count,model.formats,
        model.languages,model.media_type, model.subjects, model.title,model.translators)
    }

}