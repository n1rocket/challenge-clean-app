package com.abuenoben.challenge.data.repository

import com.abuenoben.challenge.R
import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.setup.network.MockController
import com.abuenoben.challenge.setup.network.ResponseResult
import com.abuenoben.challenge.setup.utils.Either

class MockFavoritesRepositoryImpl(private val mockController: MockController) :
    FavoritesRepository {
    override suspend fun favorites(): Either<ResponseResult.Failure, ResponseResult.Success<FavoritesResponse>> {
        return Either.Right(mockController.getMockResponseResult(R.raw.favorites))
    }

    override suspend fun favorite(id: String): Either<ResponseResult.Failure, ResponseResult.Success<FavoriteResponse>> {
        return Either.Right(mockController.getMockResponseResult(R.raw.favorite))
    }
}
