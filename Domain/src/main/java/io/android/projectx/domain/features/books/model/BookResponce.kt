package io.android.projectx.domain.features.books.model

data class BookResponce(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)