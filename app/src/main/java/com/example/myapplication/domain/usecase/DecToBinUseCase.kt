package com.example.myapplication.domain.usecase

class DecToBinUseCase {

    fun execute(decValue: String): String {
        return decValue.toInt(10).toString(2)
    }
}