package com.example.myapplication.domain.usecase

class DecToBinUseCase(
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(decValue: String, precision: Int): String {
        return convertDecToUseCase.execute(decValue, 2, precision)
    }
}