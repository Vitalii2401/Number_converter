package com.example.myapplication.domain.usecase

import kotlin.math.pow

class ConvertToDecUseCase {

    fun execute(value: String, numberSystem: Int): String {
        return if (value.contains(".")) {
            val ratio = value.split(".")
            (convertToDec(ratio[0], numberSystem, false) +
                    convertToDec(ratio[1], numberSystem, true)).toString()
        } else
            convertToDec(value, numberSystem, false).toString()
    }

    private fun convertToDec(value: String, numberSystem: Int, valAfterDot: Boolean): Double {
        var decResult = 0.0
        var index = 0
        val size = value.length - 1

        if (valAfterDot)
            index = value.length

        for (it in value) {
            decResult += numberSystem.toDouble().pow((size - index).toDouble()) * it.toString()
                .toInt(numberSystem)
            index++
        }

        return decResult
    }
}