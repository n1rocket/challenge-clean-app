package com.abuenoben.challenge.setup.network

import com.abuenoben.data.FavoritesRemoteDataSource
import com.abuenoben.data.model.network.FavoriteResponse
import com.abuenoben.data.model.network.FavoritesResponse
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.NetworkExceptionController
import com.abuenoben.data.utils.ResponseResult

class FavoritesRemoteDataSourceImpl(
    private val service: FavoritesService,
    private val networkExceptionController: NetworkExceptionController
) : FavoritesRemoteDataSource {
    override suspend fun favorites(): Either<ResponseResult.Failure, ResponseResult.Success<FavoritesResponse>> {
        return try {
            val response = service.favorites()
            networkExceptionController.checkResponse(
                response.code(),
                response.body(),
                response.errorBody()?.string()
            )
        } catch (e: Exception) {
            networkExceptionController.checkException(e)
        }
    }

    override suspend fun favorite(id: String): Either<ResponseResult.Failure, ResponseResult.Success<FavoriteResponse>> {
        return try {
            val response = service.favorite(id)
            networkExceptionController.checkResponse(
                response.code(),
                response.body(),
                response.errorBody()?.string()
            )
        } catch (e: Exception) {
            networkExceptionController.checkException(e)
        }
    }
}
