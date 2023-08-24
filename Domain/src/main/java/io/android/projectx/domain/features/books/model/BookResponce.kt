package io.android.projectx.domain.features.books.model

import com.google.gson.annotations.SerializedName

data class BookResponce(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val books: List<Book>
)