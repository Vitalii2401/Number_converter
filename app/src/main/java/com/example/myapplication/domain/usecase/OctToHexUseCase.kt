package com.example.myapplication.domain.usecase

class OctToHexUseCase {

    fun execute(octValue: String): String {
        return octValue.toInt(8).toString(16)
    }
}