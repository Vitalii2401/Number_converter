package com.example.myapplication.domain.usecase

class BinToDecUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase
) {

    fun execute(binValue: String): String {
        return convertToDecUseCase.execute(binValue, 2)
    }
}