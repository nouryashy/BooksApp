package io.android.projectx.remote.features.books.model.response

import io.android.projectx.data.features.books.model.Author
import io.android.projectx.data.features.books.model.Formats
import io.android.projectx.data.features.books.model.Translator

data class Result(
    val id: Int,
    val authors: List<Author>,
    val bookshelves: List<String>,
    val copyright: Boolean,
    val download_count: Int,
    val formats: Formats,
    val languages: List<String>,
    val media_type: String,
    val subjects: List<String>,
    val title: String,
    val translators: List<Translator>
)