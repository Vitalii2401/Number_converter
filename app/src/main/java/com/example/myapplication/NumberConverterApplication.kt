package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NumberConverterApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(applicationContext)
            modules(
                listOf(
                    useCaseModule,
                    viewModelModule,
                    repositoryModule,
                    dataSourceModule,
                    appSettingsModule,
                )
            )
        }
    }
}