package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AppRepository

class ChangeAppNightModeMaskUseCase(
    private val repository: AppRepository
) {

    fun execute(nightModeMask: Int) {
        repository.changeNightModeMask(nightModeMask)
    }
}