package com.example.myapplication.domain.usecase

class OctToDecUseCase {

    fun execute(octValue: String): String {
        return octValue.toInt(8).toString()
    }
}