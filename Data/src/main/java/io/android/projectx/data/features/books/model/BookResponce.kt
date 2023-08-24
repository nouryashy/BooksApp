package io.android.projectx.data.features.books.model

data class BookResponce(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<ResultEntity>
)