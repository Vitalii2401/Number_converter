package com.example.myapplication.domain.usecase

class HexToBinUseCase {

    fun execute(hexValue: String): String {
        return hexValue.toInt(16).toString(2)
    }
}