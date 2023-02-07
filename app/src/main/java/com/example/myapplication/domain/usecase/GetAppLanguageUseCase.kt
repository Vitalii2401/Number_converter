package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AppRepository

class GetAppLanguageUseCase(
    private val repository: AppRepository
) {

    fun execute(): String = repository.getAppLanguage()
}