package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication.data.settings.AppSettings
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

        setupNightMode()
    }

    private fun setupNightMode() {
        val pref = getSharedPreferences(AppSettings.PREF_DB_NAME, Context.MODE_PRIVATE)
        val nightMode = pref.getInt(AppSettings.PREF_TITLE_NIGHT_MODE, AppSettings.NIGHT_MODE_DEFAULT)
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}