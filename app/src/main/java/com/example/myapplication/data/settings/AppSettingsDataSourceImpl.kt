package com.example.myapplication.data.settings

import com.example.myapplication.data.datasource.AppDataSource

class AppSettingsDataSourceImpl(
    private val appSettings: AppSettings
) : AppDataSource.Settings {

    override fun getLanguage(): String = appSettings.getLanguage()

    override fun getTheme(): String = appSettings.getTheme()

    override fun getNightModeMask(): Int = appSettings.getNightModeMask()

    override fun changeAppLanguage(newLanguage: String) {
        appSettings.changeLanguage(newLanguage)
    }

    override fun changeAppTheme(newTheme: String) {
        appSettings.changeTheme(newTheme)
    }

    override fun changeNightModeMask(newNightModeMask: Int) {
        appSettings.changeNightModeMask(newNightModeMask)
    }
}