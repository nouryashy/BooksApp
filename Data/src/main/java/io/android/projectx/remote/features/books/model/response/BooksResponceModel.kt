package io.android.projectx.remote.features.books.model.response

import com.google.gson.annotations.SerializedName
import io.android.projectx.domain.features.books.model.Book

class BooksResponceModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val books: List<Book>
)