package com.example.myapplication.domain.usecase

class HexToOctUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase,
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(hexValue: String): String {
        return convertDecToUseCase.execute(
            convertToDecUseCase.execute(hexValue, 16),
            8
        )
    }
}