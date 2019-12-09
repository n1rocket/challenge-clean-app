package com.abuenoben.challenge.setup.di

import com.abuenoben.challenge.data.repository.FavoritesRepository
import com.abuenoben.challenge.data.usecases.GetFavoriteUseCase
import com.abuenoben.challenge.data.usecases.GetFavoritesUseCase
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
