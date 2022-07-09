package com.example.myapplication.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.myapplication.R
import com.example.myapplication.utility.*

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        getSharedPref()
    }

    private fun getSharedPref() {
        val sharedPreferences = requireContext().getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE)

        val language = sharedPreferences.getString(PREF_TITLE_LANG, LANGUAGE_DEFAULT)
        val theme = sharedPreferences.getString(PREF_TITLE_THEME, THEME_DEFAULT)

        setupListPref(language, theme)
    }

    private fun setupListPref(language: String?, theme: String?) {
        val languagePreferences = findPreference<ListPreference>(PREF_TITLE_LANG)
        val themePreferences = findPreference<ListPreference>(PREF_TITLE_THEME)

        languagePreferences?.let {
            initLanguagePref(language, it)
            it.setOnPreferenceChangeListener { _, newValue ->
                handleChangeLanguage(newValue.toString())
                true
            }
        }

        themePreferences?.let {
            initThemePref(theme, it)
            it.setOnPreferenceChangeListener { _, newValue ->
                handleChangeTheme(newValue.toString())
                true
            }
        }
    }

    private fun initLanguagePref(language: String?, it: ListPreference) {
        val arrayLanguage = requireContext().resources.getStringArray(R.array.language_array)
        val languageCode = when(language) {
            LANGUAGE_EN -> arrayLanguage[0]
            LANGUAGE_UK -> arrayLanguage[1]
            else -> arrayLanguage[0]
        }

        it.value = languageCode
    }

    private fun initThemePref(theme: String?, it: ListPreference) {
        val arrayTheme = requireContext().resources.getStringArray(R.array.theme_array)
        it.value = when (theme) {
            THEME_ORANGE -> arrayTheme[0]
            THEME_GREEN -> arrayTheme[1]
            THEME_BLUE -> arrayTheme[2]
            THEME_VIOLET -> arrayTheme[3]
            else -> arrayTheme[1]
        }
    }

    private fun handleChangeLanguage(newLanguage: String) {
        val arrayLanguage = requireContext().resources.getStringArray(R.array.language_array)
        val languageCode = when(newLanguage) {
            arrayLanguage[0] -> LANGUAGE_EN
            arrayLanguage[1] -> LANGUAGE_UK
            else -> LANGUAGE_EN
        }

        requireContext().getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE).edit().putString(
            PREF_TITLE_LANG, languageCode).apply()

        requireActivity().recreate()
    }

    private fun handleChangeTheme(newTheme: String) {
        val arrayTheme = requireContext().resources.getStringArray(R.array.theme_array)
        val theme = when (newTheme) {
            arrayTheme[0] -> THEME_ORANGE
            arrayTheme[1] -> THEME_GREEN
            arrayTheme[2] -> THEME_BLUE
            arrayTheme[3] -> THEME_VIOLET
            else -> THEME_GREEN
        }

        requireContext().getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE).edit().putString(
            PREF_TITLE_THEME, theme).apply()

        requireActivity().recreate()
    }
}