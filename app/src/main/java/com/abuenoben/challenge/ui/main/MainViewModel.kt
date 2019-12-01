package com.abuenoben.challenge.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abuenoben.challenge.setup.network.FavoritesRepository
import com.abuenoben.challenge.setup.network.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: FavoritesRepository) : ViewModel() {

    private val items = MutableLiveData<List<String>>()

    fun getItems(): LiveData<List<String>> {
        return items
    }

    fun refreshList() {
        launchDataLoad()
    }

    fun loadItems() {
        launchDataLoad()
    }

    private fun launchDataLoad() {
        viewModelScope.launch {
            requestItems()
            // Modify UI
        }
    }

    private suspend fun requestItems() = withContext(Dispatchers.Default) {
        // Heavy work
        when (val response = repository.favorites()) {
            is ResponseResult.Success -> {
                print(response.value)
                items.postValue(response.value.result)
            }
            else -> {
                print("Error Request")
                items.postValue(listOf())
            }
        }
    }

    fun itemClicked(itemClicked: String) {
        //Item Clicked from list
    }
}
