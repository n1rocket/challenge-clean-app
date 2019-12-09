package com.abuenoben.challenge.data.usecases

import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.repository.FavoritesRepository
import com.abuenoben.challenge.setup.network.ResponseResult
import com.abuenoben.challenge.setup.usecase.BaseUseCase
import com.abuenoben.challenge.setup.utils.Either

class GetFavoriteUseCase(
    private val repository: FavoritesRepository
) : BaseUseCase<ResponseResult.Success<FavoriteResponse>, GetFavoriteUseCase.Params>() {

    override suspend fun run(params: Params): Either<ResponseResult.Failure, ResponseResult.Success<FavoriteResponse>> {
        return repository.favorite(params.id)
    }

    data class Params(val id: String)

}