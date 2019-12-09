package com.abuenoben.data

import com.abuenoben.data.model.network.FavoriteResponse
import com.abuenoben.data.model.network.FavoritesResponse
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.ResponseResult

interface FavoritesRemoteDataSource {
    suspend fun favorites(): Either<ResponseResult.Failure, ResponseResult.Success<FavoritesResponse>>

    suspend fun favorite(id: String): Either<ResponseResult.Failure, ResponseResult.Success<FavoriteResponse>>
}