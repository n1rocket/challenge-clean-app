package com.abuenoben.challenge.setup

import android.app.Application
import android.content.Context
import com.abuenoben.challenge.setup.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        configureDi(this)
    }

    // CONFIGURATION ---
    private fun configureDi(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(provideComponent())
        }
    }

    private fun provideComponent(): List<Module> {
        return appComponent
    }
}