package com.example.myapplication.data.settings

import com.example.myapplication.data.datasource.AppDataSource

class AppSettingsDataSourceImpl(
    private val appSettings: AppSettings
) : AppDataSource.Settings {

    override fun getLanguage(): String = appSettings.getLanguage()

    override fun getTheme(): String = appSettings.getTheme()

    override fun getNightModeMask(): Int = appSettings.getNightModeMask()
}