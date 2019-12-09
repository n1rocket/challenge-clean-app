package com.abuenoben.domain.usecases

import com.abuenoben.data.FavoritesRepository
import com.abuenoben.data.model.local.Favorite
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.ResponseResult

class GetFavoriteUseCase(
    private val repository: FavoritesRepository
) : BaseUseCase<Favorite, GetFavoriteUseCase.Params>() {

    override suspend fun run(params: Params): Either<ResponseResult.Failure, Favorite> {
        return repository.favorite(params.id)
    }

    data class Params(val id: String)

}