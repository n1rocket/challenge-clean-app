package com.abuenoben.challenge.setup.di

import com.abuenoben.data.FavoritesRepository
import com.abuenoben.domain.usecases.GetFavoriteUseCase
import com.abuenoben.domain.usecases.GetFavoritesUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { provideGetFavoritesUseCase(get()) }
    single { provideGetFavoriteUseCase(get()) }
}


fun provideGetFavoritesUseCase(favoritesRepository: FavoritesRepository): GetFavoritesUseCase {
    return GetFavoritesUseCase(favoritesRepository)
}

fun provideGetFavoriteUseCase(favoritesRepository: FavoritesRepository): GetFavoriteUseCase {
    return GetFavoriteUseCase(favoritesRepository)
}
