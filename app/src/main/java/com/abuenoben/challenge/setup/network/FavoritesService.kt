package com.abuenoben.challenge.setup.network

import com.abuenoben.data.model.network.FavoriteResponse
import com.abuenoben.data.model.network.FavoritesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface FavoritesService {

    @GET("/favorites")
    suspend fun favorites(): Response<FavoritesResponse>

    @GET("/favorites/{id}")
    suspend fun favorite(
        @Path("id") id: String
    ): Response<FavoriteResponse>
}
