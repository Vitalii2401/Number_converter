package com.example.myapplication.data.settings

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.LocaleManagerCompat
import com.example.myapplication.ui.settings.SettingsFragment

class AppSettings(private val application: Application) {

    private val sharedPreferences: SharedPreferences
        get() = application.getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE)

    private val defaultLocale
        get() = LocaleManagerCompat.getSystemLocales(application)[0]?.language

    fun getLanguage(): String {
        return sharedPreferences.getString(PREF_TITLE_LANG, defaultLocale).toString()
    }

    fun getTheme(): String {
        return sharedPreferences.getString(PREF_TITLE_THEME, THEME_DEFAULT).toString()
    }

    fun getNightModeMask(): Int {
        return sharedPreferences.getInt(PREF_TITLE_NIGHT_MODE, NIGHT_MODE_DEFAULT)
    }

    fun changeLanguage(language: String) {
        sharedPreferences.edit().putString(PREF_TITLE_LANG, language).apply()
    }

    fun changeTheme(theme: String) {
        sharedPreferences.edit().putString(PREF_TITLE_THEME, theme).apply()
    }

    fun changeNightModeMask(nightModeMask: Int) {
        sharedPreferences.edit().putInt(PREF_TITLE_NIGHT_MODE, nightModeMask).apply()
    }

    companion object {
        const val PREF_DB_NAME = "app"
        private const val PREF_TITLE_LANG = "appLanguage"
        private const val PREF_TITLE_THEME = "appTheme"
        const val PREF_TITLE_NIGHT_MODE = "nightMode"

        private const val THEME_DEFAULT = SettingsFragment.THEME_GREEN
        const val NIGHT_MODE_DEFAULT = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }
}