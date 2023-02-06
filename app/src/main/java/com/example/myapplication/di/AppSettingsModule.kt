package com.example.myapplication.di

import com.example.myapplication.data.settings.AppSettings
import org.koin.dsl.module

val appSettingsModule = module {

    single<AppSettings> {
        AppSettings(
            application = get()
        )
    }
}