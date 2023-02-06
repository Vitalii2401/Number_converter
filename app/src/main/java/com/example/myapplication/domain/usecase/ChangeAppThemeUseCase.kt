package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AppRepository

class ChangeAppThemeUseCase(
    private val repository: AppRepository
) {

    fun execute(theme: String) {
        repository.changeAppTheme(theme)
    }
}