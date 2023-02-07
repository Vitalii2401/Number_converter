package com.example.myapplication.di

import com.example.myapplication.data.datasource.AppDataSource
import com.example.myapplication.data.settings.AppSettingsDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {

    single<AppDataSource.Settings> {
        AppSettingsDataSourceImpl(
            appSettings = get()
        )
    }
}