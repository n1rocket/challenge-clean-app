package com.abuenoben.challenge.setup.di

import com.abuenoben.challenge.ui.detail.FavoriteViewModel
import com.abuenoben.challenge.ui.main.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { FavoritesViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}