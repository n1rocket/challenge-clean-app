package com.abuenoben.challenge.data.repository

import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.setup.network.ResponseResult

interface FavoritesRepository {
    suspend fun favorites(): ResponseResult<FavoritesResponse>

    suspend fun favorite(id: String): ResponseResult<FavoriteResponse>
}