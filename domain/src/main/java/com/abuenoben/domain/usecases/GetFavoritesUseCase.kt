package com.abuenoben.domain.usecases

import com.abuenoben.data.FavoritesRepository
import com.abuenoben.data.model.local.Favorites
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.ResponseResult

class GetFavoritesUseCase(
    private val repository: FavoritesRepository
) : BaseUseCase<Favorites, GetFavoritesUseCase.Params>() {

    override suspend fun run(params: Params): Either<ResponseResult.Failure, Favorites> {
        return repository.favorites()
    }

    class Params
}