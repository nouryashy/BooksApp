package io.android.projectx.remote.features.books.model.response

import io.android.projectx.domain.features.books.model.Result

class BooksResponceModel (
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>)