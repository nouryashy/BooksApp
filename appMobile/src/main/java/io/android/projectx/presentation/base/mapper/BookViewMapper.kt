package io.android.projectx.presentation.base.mapper

import io.android.projectx.domain.features.books.model.Result
import io.android.projectx.presentation.base.model.ResultView
import javax.inject.Inject

open class BookViewMapper @Inject constructor() : Mapper<ResultView, Result> {

    override fun mapToView(type: Result): ResultView {
        return ResultView(
        type.id,type.authors,type.bookshelves,type.copyright, type.download_count,type.formats,
        type.languages, type.media_type, type.subjects, type.title, type.translators
        )
    }
}