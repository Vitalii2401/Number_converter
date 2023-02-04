package com.example.myapplication.domain.usecase

import java.math.BigDecimal
import java.util.*

class ConvertDecToUseCase {

    fun execute(value: String, toNumberSystem: Int, precision: Int): String {
        return convertDecTo(value, toNumberSystem.toBigDecimal(), precision)
    }

    private fun convertDecTo(
        inputValue: String,
        toNumberSystem: BigDecimal,
        precision: Int
    ): String {
        var result = ""
        var isFractional = false
        var precisions = precision
        var integerValue = inputValue.toBigDecimal().toBigInteger().toBigDecimal()
        var fractionalValue = BigDecimal(0)

        if (inputValue.contains('.')) {
            fractionalValue = inputValue.toBigDecimal().subtract(integerValue)
            isFractional = true
            result += "."
        }

        while (integerValue >= toNumberSystem) {
            result += (integerValue.rem(toNumberSystem)).toInt().toString(toNumberSystem.toInt())
                .uppercase(Locale.ENGLISH)
            integerValue = integerValue.divide(toNumberSystem)
        }

        result += integerValue.toBigInteger().toString(toNumberSystem.toInt())
            .uppercase(Locale.ENGLISH)
        result = result.reversed()

        while (precisions > 0 && isFractional) {
            fractionalValue = fractionalValue.multiply(toNumberSystem)
            result += fractionalValue.toBigInteger().toString(toNumberSystem.toInt())
                .uppercase(Locale.ENGLISH)

            if (fractionalValue >= BigDecimal.ONE)
                fractionalValue =
                    fractionalValue.subtract(fractionalValue.toBigInteger().toBigDecimal())

            if (fractionalValue.compareTo(BigDecimal.ZERO) == 0)
                break

            precisions--
        }

        return result
    }
}