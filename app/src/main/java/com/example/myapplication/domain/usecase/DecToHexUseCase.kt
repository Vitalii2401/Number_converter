package com.example.myapplication.domain.usecase

class DecToHexUseCase(
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(decValue: String): String {
        return convertDecToUseCase.execute(decValue, 16)
    }
}