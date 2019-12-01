package com.abuenoben.challenge.setup.di

import com.abuenoben.challenge.ui.detail.DetailViewModel
import com.abuenoben.challenge.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}