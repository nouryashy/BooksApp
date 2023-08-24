package com.example.newscleanapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.android.projectx.cache.features.books.dao.CachedBooksDao
import io.android.projectx.data.features.books.BooksDataRepository
import io.android.projectx.data.features.books.mapper.BookMapper
import io.android.projectx.data.features.books.repository.BooksCache
import io.android.projectx.data.features.books.repository.BooksRemote
import io.android.projectx.data.features.books.store.BooksDataStoreFactory
import io.android.projectx.domain.features.books.repository.BooksRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(mapper: BookMapper, booksCache: BooksCache, factory: BooksDataStoreFactory
    ): BooksRepository {
        return BooksDataRepository(mapper, booksCache,factory)
    }
}