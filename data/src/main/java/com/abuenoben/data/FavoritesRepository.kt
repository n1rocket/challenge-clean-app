package com.abuenoben.data

import com.abuenoben.data.model.local.Favorite
import com.abuenoben.data.model.local.Favorites
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.ResponseResult

interface FavoritesRepository {
    suspend fun favorites(): Either<ResponseResult.Failure, Favorites>

    suspend fun favorite(id: String): Either<ResponseResult.Failure, Favorite>
}