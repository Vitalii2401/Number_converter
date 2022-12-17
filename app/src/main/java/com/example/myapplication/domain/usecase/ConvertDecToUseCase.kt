package com.example.myapplication.domain.usecase

import java.math.*

class ConvertDecToUseCase {

    fun execute(value: String, toNumberSystem: Int, precision: Int): String {
        return convertDecTo(value, toNumberSystem.toBigDecimal(), precision)
    }

    private fun convertDecTo(inputValue: String, toNumberSystem: BigDecimal, precision: Int): String {
        var result = ""
        var isFractional = false
        var precisions = precision
        var integerValue= inputValue.toBigDecimal().toBigInteger().toBigDecimal()
        var fractionalValue= BigDecimal(0)

        if(inputValue.contains('.')) {
            fractionalValue = inputValue.toBigDecimal().subtract(integerValue)
            isFractional = true
            result += "."
        }

        while (integerValue >= toNumberSystem) {
            result += (integerValue.rem(toNumberSystem)).toInt().toString(toNumberSystem.toInt())
            integerValue = integerValue.divide(toNumberSystem)
        }

        result += integerValue.toBigInteger().toString(toNumberSystem.toInt())
        result = result.reversed()

        while (precisions > 0 && isFractional) {
            fractionalValue = fractionalValue.multiply(toNumberSystem)
            result += fractionalValue.toBigInteger().toString(toNumberSystem.toInt())

            if (fractionalValue >= BigDecimal.ONE)
                fractionalValue =
                    fractionalValue.subtract(fractionalValue.toBigInteger().toBigDecimal())

            if(fractionalValue.compareTo(BigDecimal.ZERO) == 0)
                break

            precisions--
        }

        return result
    }

    /*
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

     */
}