package com.example.myapplication.domain.usecase

class BinToOctUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase,
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(binValue: String): String {
        return convertDecToUseCase.execute(
            convertToDecUseCase.execute(binValue, 2),
            8,
            256
        )
    }
}