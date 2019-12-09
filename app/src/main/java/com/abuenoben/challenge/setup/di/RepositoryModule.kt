package com.abuenoben.challenge.setup.di

import com.abuenoben.challenge.data.repository.FavoritesRepository
import com.abuenoben.challenge.data.repository.FavoritesRepositoryImpl
import com.abuenoben.challenge.data.repository.FavoritesService
import com.abuenoben.challenge.setup.network.NetworkExceptionController
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { NetworkExceptionController(androidContext()) }
    single { provideFavoritesRepository(get(), get()) }
    /*factory {
        MockFavoritesRepositoryImpl(
            get()
        )
    }*/

}

fun provideFavoritesRepository(
    favoritesService: FavoritesService,
    networkExceptionController: NetworkExceptionController
): FavoritesRepository {
    return FavoritesRepositoryImpl(favoritesService, networkExceptionController)
}
