package com.abuenoben.challenge.setup.di

import com.abuenoben.challenge.setup.network.FavoritesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory { FavoritesRepository(get(), androidContext()) }
}