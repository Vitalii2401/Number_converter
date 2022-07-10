package com.example.myapplication.domain.usecase

import java.util.*

class ConvertDecToUseCase {

    fun execute(value: String, numberSystem: Int): String {
        return if (value.contains(".")) {
            val decValue = value.toDouble()
            convertDecTo(decValue.toInt(), numberSystem) + "." +
                    convertFractionalDecTo(decValue - decValue.toInt(), numberSystem, 7)
        } else
            convertDecTo(value.toInt(), numberSystem)
    }

    private fun convertDecTo(value: Int, numberSystem: Int): String {
        var result = ""
        var decValue = value

        while (decValue >= numberSystem) {
            result += (decValue % numberSystem).toString(numberSystem)
            decValue /= numberSystem
        }

        result += decValue.toString(numberSystem)

        return result.reversed()
    }

    private fun convertFractionalDecTo(value: Double, numberSystem: Int, precision: Int): String {
        var result = ""
        var decValue = value

        val valueFormat = value.toString().length - 2

        repeat(precision) {
            if (decValue >= 1)
                decValue -= decValue.toInt()

            if(decValue == 0.0)
                return@repeat

            var tmp = String.format(Locale.ENGLISH,"%.${valueFormat}f", decValue)
            if(it != 0 &&  tmp == value.toString())
                return@repeat

            decValue *= numberSystem
            result += decValue.toInt().toString(numberSystem)
        }

        return result
    }
}