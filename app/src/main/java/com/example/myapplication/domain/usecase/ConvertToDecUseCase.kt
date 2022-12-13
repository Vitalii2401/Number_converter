package com.example.myapplication.domain.usecase

import java.math.BigDecimal
import java.math.MathContext

class ConvertToDecUseCase {

    fun execute(value: String, fromNumberSystem: Int): String {
        return convertToDec(value, fromNumberSystem).toString()
    }

    private fun convertToDec(inputValue: String, fromNumberSystem: Int): BigDecimal {
        var result = BigDecimal(0)
        var powerValue =
            if (inputValue.contains('.')) inputValue.indexOf('.') - 1 else inputValue.length - 1
        val value = inputValue.replace(".", "")

        for (it in value) {
            val multiplyValue = (it.toString().toInt(fromNumberSystem)).toBigDecimal()
            val addedValue = fromNumberSystem.toBigDecimal().pow(powerValue, MathContext.DECIMAL128)
                .multiply(multiplyValue)

            result = result.add(addedValue)
            powerValue--
        }

        return result
    }
}