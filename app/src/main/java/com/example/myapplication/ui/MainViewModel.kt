package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.GetAppLanguageUseCase
import com.example.myapplication.domain.usecase.GetAppNightModeMaskUseCase
import com.example.myapplication.domain.usecase.GetAppThemeUseCase

class MainViewModel(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val getAppNightModeMaskUseCase: GetAppNightModeMaskUseCase,
    private val getAppLanguageUseCase: GetAppLanguageUseCase
) : ViewModel() {

    val currentTheme: String
        get() = getAppThemeUseCase.execute()

    val currentNightModeMask: Int
        get() = getAppNightModeMaskUseCase.execute()

    val currentLanguage: String
        get() = getAppLanguageUseCase.execute()
}