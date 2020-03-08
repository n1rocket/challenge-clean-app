package com.abuenoben.data.utils.mappers

import com.abuenoben.data.model.local.Favorites
import com.abuenoben.data.model.network.FavoritesResponse

fun FavoritesResponse.toLocal() = Favorites(result)