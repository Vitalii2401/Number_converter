package com.example.myapplication.domain.usecase

class OctToBinUseCase {

    fun execute(octValue: String): String {
        return octValue.toInt(8).toString(2)
    }
}