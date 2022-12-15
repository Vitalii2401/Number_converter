package com.example.myapplication.utility

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication.R
import java.util.*

object LocaleService {

    fun updateBaseContextLocale(context: Context): Context {
        val language = getLanguage(context)
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
        return context
    }

    fun updateAppTheme(context: Context) {
        when (getTheme(context)) {
            THEME_ORANGE -> context.setTheme(R.style.Theme_NumberConverterOrange)
            THEME_BLUE -> context.setTheme(R.style.Theme_NumberConverterBlue)
            THEME_VIOLET -> context.setTheme(R.style.Theme_NumberConverterPurple)
            else -> context.setTheme(R.style.Theme_NumberConverterGreen)
        }

        AppCompatDelegate.setDefaultNightMode(getNightModeMask(context))
    }

    private fun getSharedPref(context: Context): SharedPreferences =
        context.getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE)

    private fun getLanguage(context: Context): String =
        getSharedPref(context).get(PREF_TITLE_LANG, LANGUAGE_DEFAULT)

    private fun getTheme(context: Context): String =
        getSharedPref(context).get(PREF_TITLE_THEME, THEME_DEFAULT)

    private fun getNightModeMask(context: Context): Int =
        getSharedPref(context).get(PREF_TITLE_NIGHT_MODE, NIGHT_MODE_DEFAULT)
}