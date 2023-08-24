package io.android.projectx.data.features.books.store

import io.android.projectx.data.features.books.model.BookEntity
import io.android.projectx.data.features.books.repository.BooksRemote
import io.android.projectx.remote.features.books.mapper.BooksResponseModelMapper
import io.android.projectx.remote.features.books.service.BooksService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class BooksRemoteDataStore @Inject constructor(
    private val service: BooksService,
    private val mapper: BooksResponseModelMapper,
    @Named("api.key.books") private val apiKey: String
) : BooksRemote {
        override fun getBooks(): Flow<List<BookEntity>> {
        return service.getBooks().map {
            it.books!!.map { model ->
                mapper.mapFromModel(model)
            }
        }
    }



}