package io.android.projectx.data.features.books

import io.android.projectx.data.features.books.mapper.BookMapper
import io.android.projectx.data.features.books.repository.BooksCache
import io.android.projectx.data.features.books.store.BooksDataStoreFactory
import io.android.projectx.domain.features.books.model.Book
import io.android.projectx.domain.features.books.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BooksDataRepository @Inject constructor(
    private val mapper: BookMapper,
    private val cache: BooksCache,
    private val factory: BooksDataStoreFactory
) : BooksRepository {

    override suspend fun getBooks(): Flow<List<Book>> {
        val (areCached, isExpired) = cache.areBooksCached() to cache.isBooksCacheExpired()

        val dataStore = factory.getDataStore(areCached, isExpired)
        val books = dataStore.getBooks()

        factory.getCacheDataStore().saveBooks(books)
        return books.map { it.map { mapper.mapFromEntity(it) }

    }}
}