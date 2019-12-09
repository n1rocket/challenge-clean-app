package com.abuenoben.challenge.data.repository

import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.setup.network.NetworkExceptionController
import com.abuenoben.challenge.setup.network.ResponseResult
import com.abuenoben.challenge.setup.utils.Either

class FavoritesRepositoryImpl(
    private val service: FavoritesService,
    private val networkExceptionController: NetworkExceptionController
) : FavoritesRepository {
    override suspend fun favorites(): Either<ResponseResult.Failure, ResponseResult.Success<FavoritesResponse>> {
        return try {
            val response = service.favorites()
            networkExceptionController.checkResponse(response)
        } catch (e: Exception) {
            networkExceptionController.checkException(e)
        }
    }

    override suspend fun favorite(id: String): Either<ResponseResult.Failure, ResponseResult.Success<FavoriteResponse>> {
        return try {
            val response = service.favorite(id)
            networkExceptionController.checkResponse(response)
        } catch (e: Exception) {
            networkExceptionController.checkException(e)
        }
    }
}
