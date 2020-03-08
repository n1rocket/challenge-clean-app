package com.abuenoben.data

import com.abuenoben.data.model.local.Favorite
import com.abuenoben.data.model.local.Favorites
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.ResponseResult
import com.abuenoben.data.utils.map
import com.abuenoben.data.utils.mappers.toLocal

class FavoritesRepositoryImpl(
    private val remote: FavoritesRemoteDataSource
) : FavoritesRepository {
    override suspend fun favorites(): Either<ResponseResult.Failure, Favorites> {
        return remote.favorites().map { it.value.toLocal() }
    }

    override suspend fun favorite(id: String): Either<ResponseResult.Failure, Favorite> {
        return remote.favorite(id).map { it.value.toLocal() }
    }
}
