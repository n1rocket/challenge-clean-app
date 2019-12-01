package com.abuenoben.challenge.setup.network

import com.abuenoben.challenge.data.model.FavoriteResponse
import com.abuenoben.challenge.data.model.FavoritesResponse
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
