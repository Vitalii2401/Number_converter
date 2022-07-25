package com.example.myapplication.domain.usecase

import java.math.BigDecimal

class ConvertDecToUseCase {

    fun execute(value: String, numberSystem: Int): String =
        if (value.contains(".")) {
            val decValue = value.toBigDecimal()
            convertDecTo(decValue.toInt().toBigDecimal(), numberSystem) + "." +
                    convertFractionalDecTo(decValue.minus(decValue.toInt().toBigDecimal()), numberSystem, 25)
        } else
            convertDecTo(value.toBigDecimal(), numberSystem)


    private fun convertDecTo(value: BigDecimal, numberSystem: Int): String {
        var result = ""
        var decValue = value

        while (decValue >= numberSystem.toBigDecimal()) {
            result += (decValue.rem(numberSystem.toBigDecimal())).toInt().toString(numberSystem)
            decValue = decValue.divide(numberSystem.toBigDecimal())
        }

        result += decValue.toInt().toString(numberSystem)

        return result.reversed()
    }

    private fun convertFractionalDecTo(value: BigDecimal, numberSystem: Int, precision: Int): String {
        var result = ""
        var decValue = value

        repeat(precision) {
            if (decValue >= BigDecimal.ONE)
                decValue = decValue.minus(decValue.toInt().toBigDecimal())

            if(decValue.toDouble() == 0.0)
                return@repeat

            if(it != 0 &&  decValue == value)
                return@repeat

            decValue = decValue.multiply(numberSystem.toBigDecimal())
            result += decValue.toInt().toString(numberSystem)
        }

        return result
    }
}