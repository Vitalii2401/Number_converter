package com.example.myapplication.ui.settings

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.ChangeAppLanguageUseCase
import com.example.myapplication.domain.usecase.ChangeAppNightModeMaskUseCase
import com.example.myapplication.domain.usecase.ChangeAppThemeUseCase

class SettingsViewModel(
    private val changeAppThemeUseCase: ChangeAppThemeUseCase,
    private val changeAppLanguageUseCase: ChangeAppLanguageUseCase,
    private val changeAppNightModeMaskUseCase: ChangeAppNightModeMaskUseCase,
) : ViewModel() {

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