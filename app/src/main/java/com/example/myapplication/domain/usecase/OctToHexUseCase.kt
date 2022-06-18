package com.example.myapplication.domain.usecase

class OctToHexUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase,
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(octValue: String): String {
        return convertDecToUseCase.execute(
            convertToDecUseCase.execute(octValue, 8),
            16
        )
    }
}