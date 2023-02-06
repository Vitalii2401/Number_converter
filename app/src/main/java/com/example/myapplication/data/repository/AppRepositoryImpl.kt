package com.example.myapplication.data.repository

import com.example.myapplication.data.datasource.AppDataSource
import com.example.myapplication.domain.repository.AppRepository

class AppRepositoryImpl(
    private val appSettingsDataSource: AppDataSource.Settings
) : AppRepository {

    override fun getAppLanguage(): String = appSettingsDataSource.getLanguage()

    override fun getAppTheme(): String = appSettingsDataSource.getTheme()

    override fun getNightModeMask(): Int = appSettingsDataSource.getNightModeMask()

    override fun changeAppLanguage(newLanguage: String) {
        appSettingsDataSource.changeAppLanguage(newLanguage)
    }

    override fun changeAppTheme(newTheme: String) {
        appSettingsDataSource.changeAppTheme(newTheme)
    }

    override fun changeNightModeMask(newNightModeMask: Int) {
        appSettingsDataSource.changeNightModeMask(newNightModeMask)
    }
}