package com.example.myapplication.domain.usecase

import kotlin.math.pow

class BinToDecUseCase {

    fun execute(binValue: String): String {
        return convertBinToDec(binValue)
    }

    private fun convertBinToDec(bin: String): String {
        var decResult = 0
        var index = 0
        val size = bin.length - 1

        for (it in bin) {
            decResult += 2.0.pow(size - index).toInt() * it.digitToInt()
            index++
        }

        return decResult.toString()
    }
}