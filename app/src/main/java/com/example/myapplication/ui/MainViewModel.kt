package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.GetAppThemeUseCase

class MainViewModel(
    private val getAppThemeUseCase: GetAppThemeUseCase
) : ViewModel() {

    val currentTheme: String
        get() = getAppThemeUseCase.execute()
}