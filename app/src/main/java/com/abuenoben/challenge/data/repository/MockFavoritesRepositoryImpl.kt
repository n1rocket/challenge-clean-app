package com.abuenoben.challenge.data.repository

import com.abuenoben.challenge.R
import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.setup.network.MockController
import com.abuenoben.challenge.setup.network.ResponseResult

class MockFavoritesRepositoryImpl(private val mockController: MockController) :
    FavoritesRepository {
    override suspend fun favorites(): ResponseResult<FavoritesResponse> {
        return mockController.getMockResponseResult(R.raw.favorites)
    }

    override suspend fun favorite(id: String): ResponseResult<FavoriteResponse> {
        return mockController.getMockResponseResult(R.raw.favorite)
    }
}
