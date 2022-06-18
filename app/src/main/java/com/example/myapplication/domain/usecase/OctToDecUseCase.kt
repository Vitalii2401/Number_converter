package com.example.myapplication.domain.usecase

class OctToDecUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase
) {

    fun execute(octValue: String): String {
        return convertToDecUseCase.execute(octValue, 8)
    }
}