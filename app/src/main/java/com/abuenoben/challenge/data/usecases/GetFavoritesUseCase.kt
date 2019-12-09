package com.abuenoben.challenge.data.usecases

import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.data.repository.FavoritesRepository
import com.abuenoben.challenge.setup.network.ResponseResult
import com.abuenoben.challenge.setup.usecase.BaseUseCase
import com.abuenoben.challenge.setup.utils.Either

class GetFavoritesUseCase(
    private val repository: FavoritesRepository
) : BaseUseCase<ResponseResult.Success<FavoritesResponse>, GetFavoritesUseCase.Params>() {

    override suspend fun run(params: Params): Either<ResponseResult.Failure, ResponseResult.Success<FavoritesResponse>> {
        return repository.favorites()
    }

    class Params
}