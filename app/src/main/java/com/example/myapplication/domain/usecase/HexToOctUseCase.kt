package com.example.myapplication.domain.usecase

class HexToOctUseCase {

    fun execute(hexValue: String): String {
        return hexValue.toInt(16).toString(8)
    }
}