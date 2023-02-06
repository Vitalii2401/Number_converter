package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AppRepository

class ChangeAppLanguageUseCase(
    private val repository: AppRepository
) {

    fun execute(language: String) {
        repository.changeAppLanguage(language)
    }
}