package com.example.myapplication.domain.usecase

import com.example.myapplication.utility.PRECISION
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class ConvertToDecUseCase {

    fun execute(value: String, numberSystem: Int): String =
        if (value.contains(".")) {
            val ratio = value.split(".")
            (convertToDec(ratio[0], numberSystem, false).add(
                convertToDec(ratio[1], numberSystem, true)))
                .toString()
        } else {
            convertToDec(value, numberSystem, false).toString()
        }


    private fun convertToDec(value: String, numberSystem: Int, isValueAfterDot: Boolean): BigDecimal {
        var result = BigDecimal(0)
        var index = if(isValueAfterDot) value.length else 0
        val size = value.length - 1

        var multiplyValue: BigDecimal
        var addedValue: BigDecimal
        var powerValue: Int


        for (it in value) {
            powerValue = size - index
            multiplyValue = (it.toString().toInt(numberSystem)).toBigDecimal()
            addedValue = numberSystem.toBigDecimal().pow(powerValue, MathContext.DECIMAL128).multiply(multiplyValue)

            result = result.add(addedValue)
            index++
        }

        return result
    }
}