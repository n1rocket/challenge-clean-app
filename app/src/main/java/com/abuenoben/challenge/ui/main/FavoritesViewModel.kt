package com.abuenoben.challenge.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.data.usecases.GetFavoritesUseCase
import com.abuenoben.challenge.setup.network.ResponseResult
import com.abuenoben.challenge.setup.utils.SingleLiveEvent

class FavoritesViewModel(private val getFavoritesUseCase: GetFavoritesUseCase) : ViewModel() {

    sealed class FavoritesState {
        object Loading : FavoritesState()
        object Empty : FavoritesState()
        data class Success(val favorites: List<String>) : FavoritesState()
        data class Error(val message: String) : FavoritesState()
    }

    private var data: MutableList<String> = mutableListOf()
    private val navigationToDetail = SingleLiveEvent<String>()
    private val stateLiveData = MutableLiveData<FavoritesState>()

    private lateinit var state: FavoritesState

    fun navigationToDetail(): SingleLiveEvent<String> {
        return navigationToDetail
    }

    fun stateLiveData(): LiveData<FavoritesState> {
        return stateLiveData
    }

    fun refreshList() {
        launchDataLoad()
    }

    fun loadItems() {
        if (data.isEmpty()) {
            launchDataLoad()
        } else {
            updateUI(FavoritesState.Success(data))
        }
    }

    private fun updateUI(newState: FavoritesState? = null) {
        if (newState != null) {
            state = newState
        }
        stateLiveData.value = state
    }

    private fun launchDataLoad() {
        updateUI(FavoritesState.Loading)
        val params = GetFavoritesUseCase.Params()
        getFavoritesUseCase.invoke(viewModelScope, params) {
            it.either(
                ::handleFailure,
                ::handleSuccess
            )
        }

    }

    private fun handleSuccess(response: ResponseResult.Success<FavoritesResponse>) {
        data.clear()
        data.addAll(response.value.result)
        updateUI(FavoritesState.Success(data))
    }

    private fun handleFailure(failure: ResponseResult.Failure) {
        updateUI(
            when (failure) {
                is ResponseResult.Failure.Error -> FavoritesState.Error(failure.message)
                is ResponseResult.Failure.Forbidden -> FavoritesState.Error("Forbidden")
                is ResponseResult.Failure.NotContent -> {
                    data.clear()
                    FavoritesState.Empty
                }
            }
        )

        if (data.isEmpty()){
            updateUI(FavoritesState.Empty)
        }
    }

    fun itemClicked(itemClicked: String) {
        //Item Clicked from list
        navigationToDetail.value = itemClicked
    }

    fun itemClickedToRemove(itemToRemove: String) {
        data.remove(itemToRemove)
        updateUI(FavoritesState.Success(data))
    }
}
