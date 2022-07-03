package com.example.myapplication.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.myapplication.R
import com.example.myapplication.utility.*
import org.intellij.lang.annotations.Language

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


    }

    private fun initLanguagePref(language: String, it: ListPreference) {
        val arrayLanguage = requireContext().resources.getStringArray(R.array.language_array)
        val languageCode = when(language) {
            LANGUAGE_EN -> arrayLanguage[0]
            LANGUAGE_UK -> arrayLanguage[1]
            else -> arrayLanguage[0]
        }

        it.value = languageCode
    }

    companion object {
        const val LANGUAGE_EN = "en"
        const val LANGUAGE_UK = "uk"
    }
}