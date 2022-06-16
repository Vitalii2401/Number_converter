package com.example.myapplication.domain.usecase

class HexToDecUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase
) {

    fun execute(hexValue: String): String {
        return convertToDecUseCase.execute(hexValue, 16)
    }
}