package com.example.myapplication.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.myapplication.R
import com.example.myapplication.utility.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {

    private val settingsViewModel by viewModel<SettingsViewModel>()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        setupPreferences()
    }

    private fun getSharedPref(): SharedPreferences =
        requireContext().getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE)

    private fun isUsingNightModeResources(): Boolean =
        when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }

    private fun setupPreferences() {

        /* Get current preferences */
        val currentLanguage =
            getSharedPref().getString(PREF_TITLE_LANG, Locale.getDefault().language)
        val currentTheme = getSharedPref().getString(PREF_TITLE_THEME, THEME_DEFAULT)

        /* Find preferences */
        val languagePreference = findPreference<ListPreference>(PREF_TITLE_LANG)
        val themePreference = findPreference<ListPreference>(PREF_TITLE_THEME)
        val nightModePreference = findPreference<SwitchPreference>(PREF_TITLE_NIGHT_MODE)

        languagePreference?.let {
            initLanguagePref(currentLanguage, it)
            it.setOnPreferenceChangeListener { _, newValue ->
                changeLanguage(newValue.toString())
                true
            }
        }

        themePreference?.let {
            initThemePref(currentTheme, it)
            it.setOnPreferenceChangeListener { _, newValue ->
                changeTheme(newValue.toString())
                true
            }
        }

        nightModePreference?.let {
            initNightModePref(it)
            it.setOnPreferenceChangeListener { _, newValue ->
                changeNightMode(newValue as Boolean)
                true
            }
        }
    }

    /* Init preferences */
    private fun initLanguagePref(language: String?, languageListPref: ListPreference) {
        val arrayLanguage = requireContext().resources.getStringArray(R.array.language_array)
        val languageCode = when (language) {
            LANGUAGE_UK -> arrayLanguage[1]
            else -> arrayLanguage[0]
        }

        languageListPref.value = languageCode
    }

    private fun initThemePref(theme: String?, themeListPref: ListPreference) {
        val arrayTheme = requireContext().resources.getStringArray(R.array.theme_array)
        themeListPref.value = when (theme) {
            THEME_ORANGE -> arrayTheme[0]
            THEME_BLUE -> arrayTheme[2]
            THEME_VIOLET -> arrayTheme[3]
            else -> arrayTheme[1]
        }
    }

    private fun initNightModePref(nightModePref: SwitchPreference) {
        nightModePref.isChecked = isUsingNightModeResources()
    }

    /* Change preferences */
    private fun changeLanguage(newLanguage: String) {
        val arrayLanguage = requireContext().resources.getStringArray(R.array.language_array)
        val languageCode = when (newLanguage) {
            arrayLanguage[1] -> LANGUAGE_UK
            else -> LANGUAGE_EN
        }

        settingsViewModel.changeLanguage(languageCode)
        requireActivity().recreate()
    }

    private fun changeTheme(newTheme: String) {
        val arrayTheme = requireContext().resources.getStringArray(R.array.theme_array)
        val theme = when (newTheme) {
            arrayTheme[0] -> THEME_ORANGE
            arrayTheme[2] -> THEME_BLUE
            arrayTheme[3] -> THEME_VIOLET
            else -> THEME_GREEN
        }

        settingsViewModel.changeTheme(theme)
        requireActivity().recreate()
    }

    private fun changeNightMode(isNightMode: Boolean) {
        val nightModeMask = when (isNightMode) {
            true -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_NO
        }

        settingsViewModel.changeNightModeMask(nightModeMask)
        AppCompatDelegate.setDefaultNightMode(nightModeMask)
    }
}