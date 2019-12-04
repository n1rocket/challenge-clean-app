package com.abuenoben.challenge.data.repository

import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.setup.network.NetworkExceptionController
import com.abuenoben.challenge.setup.network.ResponseResult

class FavoritesRepositoryImpl(
    private val service: FavoritesService,
    private val networkExceptionController: NetworkExceptionController
) : FavoritesRepository {
    override suspend fun favorites(): ResponseResult<FavoritesResponse> {
        return try {
            val response = service.favorites()
            networkExceptionController.checkResponse(response)
        } catch (e: Exception) {
            networkExceptionController.checkException(e)
        }
    }

    override suspend fun favorite(id: String): ResponseResult<FavoriteResponse> {
        return try {
            val response = service.favorite(id)
            networkExceptionController.checkResponse(response)
        } catch (e: Exception) {
            networkExceptionController.checkException(e)
        }
    }
}
