package com.example.myapplication.domain.usecase

class DecToHexUseCase {

    fun execute(decValue: String): String {
        return decValue.toInt(10).toString(16)
    }
}