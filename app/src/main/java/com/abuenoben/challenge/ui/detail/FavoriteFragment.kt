package com.abuenoben.challenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.abuenoben.challenge.R
import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.setup.extensions.lazyUnsychronized
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.favorite_fragment.*
import kotlinx.android.synthetic.main.favorites_fragment.name
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {
    private val viewModel: FavoriteViewModel by inject()

    private val favoriteID: String? by lazyUnsychronized {
        arguments?.let { FavoriteFragmentArgs.fromBundle(it).favorite }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.favorite_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Observe items from ViewModel for update list
        viewModel.stateLiveData().observe(this, Observer { onStateChange(it) })

        viewModel.loadItem(favoriteID)
    }

    private fun onStateChange(state: FavoriteViewModel.FavoriteState?) {
        when (val theState = state!!) {
            is FavoriteViewModel.FavoriteState.Loading -> changeLoading()
            is FavoriteViewModel.FavoriteState.Empty -> changeEmptyState()
            is FavoriteViewModel.FavoriteState.Success -> fillInfo(theState.favorite)
            is FavoriteViewModel.FavoriteState.Error -> view?.let {
                Snackbar.make(
                    it,
                    theState.message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun fillInfo(favorite: FavoriteResponse) {
        name.text = favorite.name
        hot.text = favorite.hot.toString()
        ricCode.text = favorite.ricCode
        category.text = favorite.category
    }

    private fun changeLoading() {
        //refresh.isRefreshing = isRefreshing
        name.text = "Loading"
        hot.text = "Loading"
        ricCode.text = "Loading"
        category.text = "Loading"
    }

    private fun changeEmptyState() {
        name.text = "No info"
        hot.text = "No info"
        ricCode.text = "No info"
        category.text = "No info"
    }
}
