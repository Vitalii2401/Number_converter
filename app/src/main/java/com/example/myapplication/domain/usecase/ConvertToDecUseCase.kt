package com.example.myapplication.domain.usecase

import kotlin.math.pow

class ConvertToDecUseCase {

    fun execute(value: String, numberSystem: Double): String {
        return if (value.contains(".")) {
            val ratio = value.split(".")
            (convertToDec(ratio[0], numberSystem, false) +
                    convertToDec(ratio[1], numberSystem, true)).toString()
        } else
            convertToDec(value, numberSystem, false).toString()
    }

    private fun convertToDec(value: String, numberSystem: Double, valAfterDot: Boolean): Double {
        var decResult = 0.0
        var index = 0
        val size = value.length - 1

        if (valAfterDot)
            index = value.length

        for (it in value) {
            decResult += numberSystem.pow(size - index) * it.digitToInt()

            index++
        }

        return decResult
    }
}