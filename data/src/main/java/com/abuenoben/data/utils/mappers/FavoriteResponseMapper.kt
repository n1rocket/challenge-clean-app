package com.abuenoben.data.utils.mappers

import com.abuenoben.data.model.local.Favorite
import com.abuenoben.data.model.network.FavoriteResponse

fun FavoriteResponse.toFavorite() = Favorite(category, hot, name, ricCode)