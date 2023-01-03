package com.example.myapplication.domain.usecase

class DecToOctUseCase(
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(decValue: String, precision: Int): String {
        return convertDecToUseCase.execute(decValue, 8, precision)
    }
}