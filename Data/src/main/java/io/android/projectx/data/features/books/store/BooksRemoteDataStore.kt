package io.android.projectx.data.features.books.store

import io.android.projectx.data.features.books.model.ResultEntity
import io.android.projectx.data.features.books.repository.BooksRemote
import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.android.projectx.remote.features.books.mapper.BooksResponseModelMapper
import io.android.projectx.remote.features.books.service.BooksService
import io.android.projectx.remote.features.recipes.mapper.RecipesResponseModelMapper
import io.android.projectx.remote.features.recipes.service.RecipesService
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Named

class BooksRemoteDataStore @Inject constructor(
    private val service: BooksService,
    private val mapper: BooksResponseModelMapper,
    @Named("api.key.books") private val apiKey: String
) : BooksRemote {
        override fun getBooks(): Flow<List<ResultEntity>> {
        return service.getBooks().map {
            it.results!!.map { model ->
                mapper.mapFromModel(model)
            }
        }
    }



}