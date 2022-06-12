package com.example.myapplication.domain.usecase

class DecToOctUseCase {

    fun execute(decValue: String): String {
        return decValue.toInt(10).toString(8)
    }
}