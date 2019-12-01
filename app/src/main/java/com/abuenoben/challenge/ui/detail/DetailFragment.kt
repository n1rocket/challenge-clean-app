package com.abuenoben.challenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.abuenoben.challenge.R
import com.abuenoben.challenge.setup.extensions.lazyUnsychronized
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.main_fragment.name
import org.koin.android.ext.android.inject

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by inject()

    private val favoriteID: String? by lazyUnsychronized {
        arguments?.let { DetailFragmentArgs.fromBundle(it).favorite }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.detail_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Observe items from ViewModel for update list
        viewModel.item().observe(this, Observer { favorite ->
            name.text = favorite.name
            hot.text = favorite.hot.toString()
            ricCode.text = favorite.ricCode
            category.text = favorite.category
        })

        viewModel.loadItem(favoriteID)
    }
}
