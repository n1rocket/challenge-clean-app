package com.abuenoben.challenge.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.setup.network.FavoritesRepository
import com.abuenoben.challenge.setup.network.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val repository: FavoritesRepository) : ViewModel() {

    private val item = MutableLiveData<FavoriteResponse>()

    fun item(): LiveData<FavoriteResponse> {
        return item
    }

    fun loadItem(id: String?) {
        launchDataLoad(id ?: "")
    }

    private fun launchDataLoad(id: String) {
        viewModelScope.launch {
            requestItem(id)
        }
    }

    // Heavy work
    private suspend fun requestItem(id: String) = withContext(Dispatchers.Default) {
        when (val response = repository.favorite(id)) {
            is ResponseResult.Success -> {
                item.postValue(response.value)
            }
            else -> {
                item.postValue(null)
            }
        }
    }
}
