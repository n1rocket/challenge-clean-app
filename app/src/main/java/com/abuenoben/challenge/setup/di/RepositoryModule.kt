package com.abuenoben.challenge.setup.di

import com.abuenoben.challenge.setup.network.FavoritesRemoteDataSourceImpl
import com.abuenoben.challenge.setup.network.FavoritesService
import com.abuenoben.challenge.setup.network.NetworkExceptionControllerImpl
import com.abuenoben.data.FavoritesRemoteDataSource
import com.abuenoben.data.FavoritesRepository
import com.abuenoben.data.FavoritesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { NetworkExceptionControllerImpl(androidContext()) }
    single { provideFavoritesRemoteDataSource(get(), get()) }
    single { provideFavoritesRepository(get()) }
    /*factory {
        MockFavoritesRepositoryImpl(
            get()
        )
    }*/

}

fun provideFavoritesRepository(
    favoritesRemoteDataSource: FavoritesRemoteDataSource
): FavoritesRepository {
    return FavoritesRepositoryImpl(favoritesRemoteDataSource)
}

fun provideFavoritesRemoteDataSource(
    favoritesService: FavoritesService,
    networkExceptionController: NetworkExceptionControllerImpl
): FavoritesRemoteDataSource {
    return FavoritesRemoteDataSourceImpl(
        favoritesService,
        networkExceptionController
    )
}
