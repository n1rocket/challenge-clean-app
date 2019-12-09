package com.abuenoben.data

import com.abuenoben.data.model.FavoriteResponse
import com.abuenoben.data.model.FavoritesResponse
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.ResponseResult

class FavoritesRepositoryImpl(
    private val remote: FavoritesRemoteDataSource
) : FavoritesRepository {
    override suspend fun favorites(): Either<ResponseResult.Failure, ResponseResult.Success<FavoritesResponse>> {
        return remote.favorites()
    }

    override suspend fun favorite(id: String): Either<ResponseResult.Failure, ResponseResult.Success<FavoriteResponse>> {
        return remote.favorite(id)
    }
}
