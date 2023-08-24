package io.android.projectx.data.features.books.store

import io.android.projectx.data.features.books.repository.BooksCache
import io.android.projectx.data.features.books.repository.BooksDataStore
import io.android.projectx.data.features.books.repository.BooksRemote
import io.android.projectx.data.features.recipes.repository.RecipesCache
import io.android.projectx.data.features.recipes.repository.RecipesDataStore
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.android.projectx.data.features.recipes.store.RecipesCacheDateStore
import io.android.projectx.data.features.recipes.store.RecipesRemoteDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksDataStoreFactory @Inject constructor(
    private val booksCacheDataStore: BooksCacheDataStore,
    private val booksRemoteDataStore: BooksRemoteDataStore
) {

    open fun getDataStore(booksCached: Boolean, cacheExpired: Flow<Boolean>): BooksDataStore {
        return if (booksCached && !cacheExpired) booksCacheDataStore
        else booksRemoteDataStore
    }

    open fun getCacheDataStore(): BooksCache {
        return booksCacheDataStore
    }

    open fun getRemoteDataStore(): BooksRemote {
        return booksRemoteDataStore
    }

}