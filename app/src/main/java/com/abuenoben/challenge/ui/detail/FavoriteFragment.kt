package com.abuenoben.challenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.abuenoben.challenge.R
import com.abuenoben.challenge.databinding.FavoriteFragmentBinding
import com.abuenoben.challenge.setup.extensions.lazyUnsychronized
import com.abuenoben.data.model.local.Favorite
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {
    private val viewModel: FavoriteViewModel by inject()

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    private val favoriteID: String? by lazyUnsychronized {
        arguments?.let { FavoriteFragmentArgs.fromBundle(it).favorite }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLiveData().observe(viewLifecycleOwner, Observer { onStateChange(it) })
        viewModel.loadItem(favoriteID)
    }

    private fun onStateChange(state: FavoriteViewModel.FavoriteState?) {
        when (val theState = state!!) {
            is FavoriteViewModel.FavoriteState.Loading -> changeLoading()
            is FavoriteViewModel.FavoriteState.Empty -> changeEmptyState()
            is FavoriteViewModel.FavoriteState.Success -> fillInfo(theState.favorite)
            is FavoriteViewModel.FavoriteState.Error -> Snackbar.make(
                binding.root,
                theState.message,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun fillInfo(favorite: Favorite) {
        binding.name.text = favorite.name
        binding.hot.text = favorite.hot.toString()
        binding.ricCode.text = favorite.ricCode
        binding.category.text = favorite.category
    }

    private fun changeLoading() {
        //refresh.isRefreshing = isRefreshing
        binding.name.text = "Loading"
        binding.hot.text = "Loading"
        binding.ricCode.text = "Loading"
        binding.category.text = "Loading"
    }

    private fun changeEmptyState() {
        binding.name.text = "No info"
        binding.hot.text = "No info"
        binding.ricCode.text = "No info"
        binding.category.text = "No info"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
