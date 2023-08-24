package io.android.projectx.data.features.books.repository

import io.android.projectx.data.features.books.model.ResultEntity
import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface BooksDataStore {


   suspend fun getBooks(): Flow<List<ResultEntity>>
}