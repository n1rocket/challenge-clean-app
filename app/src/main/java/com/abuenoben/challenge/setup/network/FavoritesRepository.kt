package com.abuenoben.challenge.setup.network

import android.content.Context
import com.abuenoben.challenge.R
import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.model.FavoritesResponse
import com.abuenoben.challenge.setup.Constants
import com.abuenoben.challenge.setup.extensions.getMockResponseResult
import com.abuenoben.challenge.setup.network.NetworkExceptionController.checkException
import com.abuenoben.challenge.setup.network.NetworkExceptionController.checkResponse

class FavoritesRepository(
    private val service: FavoritesService,
    private val context: Context
) {
    suspend fun favorites(
        fake: Boolean = Constants.isMock
    ): ResponseResult<FavoritesResponse> {
        return if (!fake) {
            try {

                val response = service.favorites()
                checkResponse(context, response)
            } catch (e: Exception) {
                checkException(context, e)
            }
        } else {
            context.getMockResponseResult(R.raw.favorites)
        }
    }
    suspend fun favorite(
        id : String,
        fake: Boolean = Constants.isMock
    ): ResponseResult<FavoriteResponse> {
        return if (!fake) {
            try {
                val response = service.favorite(id)
                checkResponse(context, response)
            } catch (e: Exception) {
                checkException(context, e)
            }
        } else {
            context.getMockResponseResult(R.raw.favorites)
        }
    }
}
