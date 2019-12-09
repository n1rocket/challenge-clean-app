package com.abuenoben.challenge.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.usecases.GetFavoriteUseCase
import com.abuenoben.challenge.setup.network.ResponseResult

class FavoriteViewModel(private val getFavoriteUseCase: GetFavoriteUseCase) : ViewModel() {
    sealed class FavoriteState {
        object Loading : FavoriteState()
        object Empty : FavoriteState()
        data class Success(val favorite: FavoriteResponse) : FavoriteState()
        data class Error(val message: String) : FavoriteState()
    }

    private var data: FavoriteResponse? = null
    private lateinit var state: FavoriteState

    private val stateLiveData = MutableLiveData<FavoriteState>()

    fun stateLiveData(): LiveData<FavoriteState> {
        return stateLiveData
    }

    private fun updateUI(newState: FavoriteState? = null) {
        if (newState != null) {
            state = newState
        }
        stateLiveData.value = state
    }

    fun loadItem(id: String?) {
        launchDataLoad(id ?: "")
    }

    private fun launchDataLoad(id: String) {
        updateUI(FavoriteState.Loading)
        val params = GetFavoriteUseCase.Params(id)
        getFavoriteUseCase.invoke(viewModelScope, params) {
            it.either(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(response: ResponseResult.Success<FavoriteResponse>) {
        data = response.value
        data?.let { updateUI(FavoriteState.Success(it)) }
    }

    private fun handleFailure(failure: ResponseResult.Failure) {
        updateUI(
            when (failure) {
                is ResponseResult.Failure.Error -> FavoriteState.Error(failure.message)
                is ResponseResult.Failure.Forbidden -> FavoriteState.Error("Forbidden")
                is ResponseResult.Failure.NotContent -> {
                    FavoriteState.Empty
                }
            }
        )

        if (data == null) {
            updateUI(FavoriteState.Empty)
        }
    }
}
