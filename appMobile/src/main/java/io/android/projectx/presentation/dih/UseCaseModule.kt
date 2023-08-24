package com.example.newscleanapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.android.projectx.domain.features.books.interactor.GetBooks
import io.android.projectx.domain.features.books.repository.BooksRepository

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(booksRepository: BooksRepository): GetBooks {
        return GetBooks(booksRepository)
    }
}