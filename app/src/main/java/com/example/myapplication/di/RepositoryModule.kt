package com.example.myapplication.di

import com.example.myapplication.data.repository.AppRepositoryImpl
import com.example.myapplication.domain.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<AppRepository> {
        AppRepositoryImpl(
            appSettingsDataSource = get()
        )
    }
}