package com.example.myapplication.domain.usecase

class HexToDecUseCase {

    fun execute(hexValue: String): String {
        return hexValue.toInt(16).toString()
    }
}