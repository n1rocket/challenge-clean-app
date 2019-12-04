package com.abuenoben.challenge.setup.di

import com.abuenoben.challenge.data.repository.FavoritesRepositoryImpl
import com.abuenoben.challenge.data.repository.MockFavoritesRepositoryImpl
import com.abuenoben.challenge.setup.network.NetworkExceptionController
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        FavoritesRepositoryImpl(
            get(),
            get()
        )
    }
    factory {
        MockFavoritesRepositoryImpl(
            get()
        )
    }
    single { NetworkExceptionController(androidContext()) }
}