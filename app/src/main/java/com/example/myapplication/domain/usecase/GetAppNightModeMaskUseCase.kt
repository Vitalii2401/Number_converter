package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AppRepository

class GetAppNightModeMaskUseCase(
    private val repository: AppRepository
) {

    fun execute(): Int = repository.getNightModeMask()
}