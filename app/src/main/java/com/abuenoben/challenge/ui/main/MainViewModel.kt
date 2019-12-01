package com.abuenoben.challenge.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abuenoben.challenge.setup.network.FavoritesRepository
import com.abuenoben.challenge.setup.network.ResponseResult
import com.abuenoben.challenge.setup.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: FavoritesRepository) : ViewModel() {

    private var data : MutableList<String> = mutableListOf()
    private val items = MutableLiveData<List<String>>()
    private val navigationToDetail = SingleLiveEvent<String>()

    fun items(): LiveData<List<String>> {
        return items
    }

    fun navigationToDetail(): SingleLiveEvent<String> {
        return navigationToDetail
    }

    fun refreshList() {
        launchDataLoad()
    }

    fun loadItems() {
        if (data.isEmpty()) {
            launchDataLoad()
        }else{
            items.postValue(data)
        }
    }

    private fun launchDataLoad() {
        viewModelScope.launch {
            requestItems()
        }
    }

    // Heavy work
    private suspend fun requestItems() = withContext(Dispatchers.Default) {
        when (val response = repository.favorites()) {
            is ResponseResult.Success -> {
                data.clear()
                data.addAll(response.value.result)
                items.postValue(data)
            }
            else -> {
                print("Error Request")
                items.postValue(listOf())
            }
        }
    }

    fun itemClicked(itemClicked: String) {
        //Item Clicked from list
        navigationToDetail.value = itemClicked
    }

    fun itemClickedToRemove(itemToRemove: String) {
        data.remove(itemToRemove)
        items.value = data
    }
}
