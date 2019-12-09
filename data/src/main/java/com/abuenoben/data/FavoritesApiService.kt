package com.abuenoben.data

import com.abuenoben.data.model.FavoriteResponse
import com.abuenoben.data.model.FavoritesResponse

// A DataSource for the Remote DB
interface FavoritesApiService {
    suspend fun favorites(): Result<FavoritesResponse>
    suspend fun favorite(id: String): Result<FavoriteResponse>
}

sealed class Result<T> {

    data class Success<T>(val value: T) : Result<T>()

    data class Failure<T>(val throwable: Throwable) : Result<T>()

}