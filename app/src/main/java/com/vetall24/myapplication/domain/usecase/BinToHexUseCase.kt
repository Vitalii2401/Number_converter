package com.vetall24.myapplication.domain.usecase

import kotlin.math.pow

class BinToHexUseCase {

    fun execute(binValue: String): String {
        return Integer.toHexString(convertBinToDec(binValue))
    }

    private fun convertBinToDec(bin: String): Int {
        var dec = 0
        var index = 0
        val size = bin.length - 1

        for (it in bin) {
            dec += 2.0.pow(size - index).toInt() * it.digitToInt()
            index++
        }

        return dec
    }
}