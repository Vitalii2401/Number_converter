package com.example.myapplication.ui.settings

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.*

class SettingsViewModel(
    private val changeAppThemeUseCase: ChangeAppThemeUseCase,
    private val changeAppLanguageUseCase: ChangeAppLanguageUseCase,
    private val changeAppNightModeMaskUseCase: ChangeAppNightModeMaskUseCase,
    private val getAppLanguageUseCase: GetAppLanguageUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase
) : ViewModel() {

    val currentTheme: String
        get() = getAppThemeUseCase.execute()

    val currentLanguage: String
        get() = getAppLanguageUseCase.execute()

    fun changeLanguage(language: String) {
        changeAppLanguageUseCase.execute(language)
    }

    fun changeTheme(theme: String) {
        changeAppThemeUseCase.execute(theme)
    }

    fun changeNightModeMask(nightModeMask: Int) {
        changeAppNightModeMaskUseCase.execute(nightModeMask)
    }
}