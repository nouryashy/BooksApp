package io.android.projectx.cache.features.books.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.android.projectx.cache.features.books.db.BooksConstants
import io.android.projectx.cache.features.recipes.db.RecipeConstants
import io.android.projectx.domain.features.books.model.Author
import io.android.projectx.domain.features.books.model.Formats
import io.android.projectx.domain.features.books.model.Translator

@Entity(tableName = BooksConstants.TABLE_NAME)
data class CachedResultBook(
    @PrimaryKey
    @ColumnInfo(name = BooksConstants.COLUMN_BOOK_ID)
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