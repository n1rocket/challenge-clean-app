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
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh.isRefreshing = true

        //Setup list with adapter
        val linearLayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = linearLayoutManager
        val adapter = MainAdapter {
            viewModel.itemClicked(it)
        }
        recycler.adapter = adapter

        //SwipeRefresh
        refresh?.setOnRefreshListener {
            viewModel.refreshList()
        }

        //Observe items from ViewModel for update list
        viewModel.items().observe(this, Observer {
            adapter.submitList(it)

            //Empty description text when list is empty
            name.visibility = when (it.isEmpty()) {
                true -> View.VISIBLE
                false -> View.GONE
            }

            refresh.isRefreshing = false
        })

        viewModel.navigationToDetail().observe(this, Observer { favoriteID ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(favoriteID)
            findNavController().navigate(action)
        })

        viewModel.loadItems()
    }
}
