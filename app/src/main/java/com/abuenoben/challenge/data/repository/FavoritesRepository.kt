package com.abuenoben.challenge.data.repository

import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.setup.network.ResponseResult
import com.abuenoben.challenge.setup.utils.Either

interface FavoritesRepository {
    suspend fun favorites(): Either<ResponseResult.Failure, ResponseResult.Success<FavoritesResponse>>

    suspend fun favorite(id: String): Either<ResponseResult.Failure, ResponseResult.Success<FavoriteResponse>>
}