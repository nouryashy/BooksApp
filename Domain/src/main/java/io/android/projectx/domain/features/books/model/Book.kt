package io.android.projectx.domain.features.books.model
import io.android.projectx.domain.features.books.model.Author
import io.android.projectx.domain.features.books.model.Formats
import io.android.projectx.domain.features.books.model.Translator

data class Book(
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