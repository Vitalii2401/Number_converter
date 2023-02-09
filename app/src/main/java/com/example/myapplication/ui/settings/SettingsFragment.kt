package com.example.myapplication.ui.settings

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.myapplication.R
import com.example.myapplication.ui.contract.HasCustomTitle
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : PreferenceFragmentCompat(), HasCustomTitle {

    interface OnSettingsChanged {

        fun applySettingsChanges()
    }

    private val settingsViewModel by viewModel<SettingsViewModel>()
    private lateinit var onSettingsChanged: OnSettingsChanged

    /* Fragment */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnSettingsChanged) {
            onSettingsChanged = context
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        setupPreferences()
    }

    /* HasCustomTitle */
    override fun getCustomTitle(): Int = R.string.fragment_settings_name

    private fun setupPreferences() {
        val languagePreference = findPreference<ListPreference>(KEY_PREF_LANGUAGE)
        val themePreference = findPreference<ListPreference>(KEY_PREF_THEME)
        val nightModePreference = findPreference<SwitchPreference>(KEY_PREF_NIGHT_MODE)

        languagePreference?.let {
            initLanguagePref(settingsViewModel.currentLanguage, it)
            it.setOnPreferenceChangeListener { _, newValue ->
                changeLanguage(newValue.toString())
                true
            }
        }

        themePreference?.let {
            initThemePref(settingsViewModel.currentTheme, it)
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

    private fun isUsingNightModeResources(): Boolean =
        when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
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
        applyChanges()
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
        applyChanges()
    }

    private fun changeNightMode(isNightMode: Boolean) {
        val nightModeMask = when (isNightMode) {
            true -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_NO
        }

        settingsViewModel.changeNightModeMask(nightModeMask)
        applyChanges()
    }

    private fun applyChanges() {
        onSettingsChanged.applySettingsChanges()
    }

    companion object {

        private const val KEY_PREF_LANGUAGE = "appLanguage"
        private const val KEY_PREF_THEME = "appTheme"
        private const val KEY_PREF_NIGHT_MODE = "nightMode"

        private const val LANGUAGE_EN = "en"
        private const val LANGUAGE_UK = "uk"

        const val THEME_ORANGE = "Orange"
        const val THEME_GREEN = "Green"
        const val THEME_BLUE = "Blue"
        const val THEME_VIOLET = "Violet"

        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}