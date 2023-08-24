package io.android.projectx.cache.features.books.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.android.projectx.cache.features.books.db.BooksConstants
import io.android.projectx.cache.features.books.model.CachedResultBook
import kotlinx.coroutines.flow.Flow

abstract class CachedBooksDao {


    @Query(BooksConstants.QUERY_BOOKS)
    @JvmSuppressWildcards
    abstract fun getBooks(): Flow<List<CachedResultBook>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertBooks(books: List<CachedResultBook>)

    @Query(BooksConstants.DELETE_BOOKS)
    abstract fun deleteBooks()


}