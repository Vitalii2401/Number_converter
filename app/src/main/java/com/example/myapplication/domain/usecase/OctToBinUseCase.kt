package com.example.myapplication.domain.usecase

class OctToBinUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase,
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(octValue: String): String {
        return convertDecToUseCase.execute(
            convertToDecUseCase.execute(octValue, 8),
            2,
            256
        )
    }
}