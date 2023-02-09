package com.example.myapplication.domain.usecase

class HexToBinUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase,
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(hexValue: String): String {
        return convertDecToUseCase.execute(
            convertToDecUseCase.execute(hexValue, 16),
            2,
            256
        )
    }
}