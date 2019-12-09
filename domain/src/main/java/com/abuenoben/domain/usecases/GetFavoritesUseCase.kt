package com.abuenoben.domain.usecases

import com.abuenoben.data.FavoritesRepository
import com.abuenoben.data.model.FavoritesResponse
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.ResponseResult

class GetFavoritesUseCase(
    private val repository: FavoritesRepository
) : BaseUseCase<ResponseResult.Success<FavoritesResponse>, GetFavoritesUseCase.Params>() {

    override suspend fun run(params: Params): Either<ResponseResult.Failure, ResponseResult.Success<FavoritesResponse>> {
        return repository.favorites()
    }

    class Params
}