package com.example.myapplication.utility

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

object LocaleService {

    fun updateBaseContextLocale(context: Context): Context {
        val language = getLanguageFromPreferences(context)
        val locale = Locale(language)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale)
        }
        return updateResourcesLocaleLegacy(context, locale)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResourcesLocale(
        context: Context,
        locale: Locale
    ): Context {
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        updateAppTheme(context)
        return context.createConfigurationContext(configuration)
    }

    @Suppress(DEPRECATION)
    private fun updateResourcesLocaleLegacy(
        context: Context,
        locale: Locale
    ): Context {
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        updateAppTheme(context)
        return context
    }

    private fun updateAppTheme(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE)
        val theme = sharedPreferences.get(PREF_TITLE_THEME, THEME_DEFAULT)
        when (theme) {
            THEME_DEFAULT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun getLanguageFromPreferences(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.get(PREF_TITLE_LANG, LANGUAGE_DEFAULT)
    }
}