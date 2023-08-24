package io.android.projectx.presentation.features.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.android.projectx.domain.features.books.interactor.GetBooks
import io.android.projectx.presentation.base.mapper.BookViewMapper
import io.android.projectx.presentation.base.model.ResultView
import io.android.projectx.presentation.base.state.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class BooksViewModel @Inject constructor(
    private val getBooks: GetBooks?,
    private val mapper: BookViewMapper
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<Resource<List<ResultView>?>>? = null
    val stateFlow: MutableStateFlow<Resource<List<ResultView>?>>? = _stateFlow


    fun getBooks(): MutableStateFlow<Resource<List<ResultView>?>>? {
        return _stateFlow
    }

    fun fetchRestaurants() {
        // Update the state to indicate loading
        updateState(Resource.loading(null))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bookList = getBooks?.invoke()
                val mappedList = bookList?.map { it.map { mapper.mapToView(it) } }

                // Update the state with the mapped data
                updateState(Resource.success(mappedList))
            } catch (e: Throwable) {
                // Handle exceptions and update the state with an error message
                updateState(Resource.error(ex = e))
            }
        }
    }

    private fun updateState(newState: Resource<Flow<List<ResultView>>?>) {
        _stateFlow!!.value = newState as Resource<List<ResultView>?>
    }

}

