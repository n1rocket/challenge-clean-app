package com.abuenoben.domain.usecases

import com.abuenoben.data.FavoritesRepository
import com.abuenoben.data.model.FavoriteResponse
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.ResponseResult

class GetFavoriteUseCase(
    private val repository: FavoritesRepository
) : BaseUseCase<ResponseResult.Success<FavoriteResponse>, GetFavoriteUseCase.Params>() {

    override suspend fun run(params: Params): Either<ResponseResult.Failure, ResponseResult.Success<FavoriteResponse>> {
        return repository.favorite(params.id)
    }

    data class Params(val id: String)

}