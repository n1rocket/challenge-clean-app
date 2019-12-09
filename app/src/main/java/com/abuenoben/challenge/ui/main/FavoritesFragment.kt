package com.abuenoben.challenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abuenoben.challenge.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.favorites_fragment.*
import org.koin.android.ext.android.inject

class FavoritesFragment : Fragment() {
    private lateinit var adapter: FavoritesAdapter
    private val viewModel: FavoritesViewModel by inject()

    private val onClickItem = object : (String) -> Unit {
        override fun invoke(item: String) {
            viewModel.itemClicked(item)
        }
    }

    private val onClickRemoveItem = object : (String) -> Unit {
        override fun invoke(item: String) {
            viewModel.itemClickedToRemove(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.favorites_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh.isRefreshing = true

        //Setup list with adapter
        val linearLayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = linearLayoutManager
        adapter = FavoritesAdapter(onClickItem, onClickRemoveItem)
        recycler.adapter = adapter

        //SwipeRefresh
        refresh?.setOnRefreshListener {
            viewModel.refreshList()
        }

        //Observe state from ViewModel
        viewModel.stateLiveData().observe(this, Observer { onStateChange(it) })

        viewModel.navigationToDetail().observe(this, Observer { favoriteID ->
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToFavoriteFragment(favoriteID)
            findNavController().navigate(action)
        })

        viewModel.loadItems()
    }

    private fun onStateChange(state: FavoritesViewModel.FavoritesState?) {
        changeEmptyState(visible = View.GONE)
        changeLoading(isRefreshing = false)
        when (val theState = state!!) {
            is FavoritesViewModel.FavoritesState.Loading -> changeLoading()
            is FavoritesViewModel.FavoritesState.Empty -> changeEmptyState()
            is FavoritesViewModel.FavoritesState.Success -> renderList(theState.favorites)
            is FavoritesViewModel.FavoritesState.Error -> Snackbar.make(recycler, theState.message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun changeLoading(isRefreshing:Boolean = true) {
        refresh.isRefreshing = isRefreshing
    }

    private fun changeEmptyState(visible : Int = View.VISIBLE) {
        name.visibility = visible
    }

    private fun renderList(favorites: List<String>) {
        adapter.submitList(favorites)
        adapter.notifyDataSetChanged()
    }

}
