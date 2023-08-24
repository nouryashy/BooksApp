package io.android.projectx.cache.features.books.db

object BooksConstants {

    const val TABLE_NAME = "books"

    const val COLUMN_BOOK_ID = "book_id"

    const val QUERY_BOOKS = "SELECT * FROM $TABLE_NAME"

    const val DELETE_BOOKS = "DELETE FROM $TABLE_NAME"


}