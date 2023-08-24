package io.android.projectx.presentation.features.books

import android.os.Bundle
import android.view.View
import android.widget.Filter
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import io.android.projectx.androidextensions.inflate
import io.android.projectx.androidextensions.initVerticalRecycler
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.Adapter
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.base.model.BookView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.extensions.updateVisibility
import kotlinx.android.synthetic.main.book_item_list.view.*
import kotlinx.android.synthetic.main.restaurants_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BooksFragment : BaseFragment(R.layout.bookmarked_fragment) {

    private lateinit var adapter: Adapter<BookView>
    private val viewModel: BooksViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
        viewModel.getBooks()
        lifecycleScope.launch {

            viewModel.getBooks()?.collect() {
                it?.let { handleDataState(it) }
            }
        }
        viewModel.fetchRestaurants()
    }

    private fun initUi() {
        adapter = Adapter(
            onCreate = { parent, _ -> Adapter.ViewHolder(parent.inflate(R.layout.book_item_list)) },
            onClick = { _, item -> onClick(item) },
            onBind = { _, item, view, _ -> onBind(item, view) },
            filter = object : Filter() {
                override fun performFiltering(constraint: CharSequence): FilterResults {
                    val sequence = constraint.toString()
                    if (sequence.isEmpty()) adapter.filteredList = adapter.items
                    else {
                        val fList: MutableList<BookView> = ArrayList()
                        for (name in adapter.items) {
                            if (name.title.toLowerCase().contains(sequence.toLowerCase()))
                                fList.add(name)
                            adapter.filteredList = fList
                        }
                    }
                    val results = FilterResults()
                    results.values = adapter.filteredList
                    return results
                }

                override fun publishResults(constraint: CharSequence, results: FilterResults) {
                    adapter.filteredList = results.values as MutableList<BookView>
                    adapter.notifyDataSetChanged()
                }
            }
        )
        recyclerRestaurants.initVerticalRecycler(adapter)
    }

    private fun <T> onClick(item: T) {
        val book = item as BookView
    }

    private fun <T> onBind(item: T, view: View) {
        val book = item as BookView
        view.book_name_tv.text = book.title
        view.book_des_tv.text = book.media_type
        Glide.with(requireContext())
            .load(book.formats.imageJPEG)
            .apply(RequestOptions.circleCropTransform())
            .into(view.book_iv)
    }

    private fun handleDataState(resource: Resource<List<BookView>?>) {
        progressbar.updateVisibility(resource.status)
        when (resource.status) {
            Resource.Status.SUCCESS -> resource.data?.let { adapter.items = it.toMutableList() }
            Resource.Status.LOADING -> {
            }
            Resource.Status.ERROR -> {
            }
            else -> {

            }
        }
    }
}