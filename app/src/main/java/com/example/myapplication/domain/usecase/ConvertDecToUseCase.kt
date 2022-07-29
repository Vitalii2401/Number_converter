package com.example.myapplication.domain.usecase

import com.example.myapplication.utility.PRECISION
import java.math.*

class ConvertDecToUseCase {

    fun execute(value: String, numberSystem: Int): String =
        if (value.contains(".")) {
            val bigDecimalValue = value.toBigDecimal()
            val intValue = bigDecimalValue.toBigInteger().toBigDecimal()
            convertDecTo(intValue, numberSystem) + "." +
                    convertFractionalDecTo(bigDecimalValue.subtract(intValue), numberSystem)
        } else {
            convertDecTo(value.toBigDecimal(), numberSystem)
        }


    private fun convertDecTo(value: BigDecimal, numberSystem: Int): String {
        var result = ""
        var decValue = value

        while (decValue >= numberSystem.toBigDecimal()) {
            result += (decValue.rem(numberSystem.toBigDecimal())).toBigInteger().toString(numberSystem)
            decValue = decValue.divide(numberSystem.toBigDecimal())
        }

        result += decValue.toBigInteger().toString(numberSystem)

        return result.reversed()
    }

    private fun convertFractionalDecTo(value: BigDecimal, numberSystem: Int): String {
        var result = ""
        var decValue = value

        repeat(PRECISION) {
            if (decValue >= BigDecimal.ONE)
                decValue = decValue.subtract(decValue.toBigInteger().toBigDecimal())

            if(decValue.compareTo(BigDecimal.ZERO) == 0)
                return@repeat

/*            if(it != 0 &&  decValue == value)
                return@repeat*/

            decValue = decValue.multiply(numberSystem.toBigDecimal())
            result += decValue.toBigInteger().toString(numberSystem)
        }

        return result
    }
}