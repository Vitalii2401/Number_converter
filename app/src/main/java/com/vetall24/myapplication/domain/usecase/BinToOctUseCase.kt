package com.vetall24.myapplication.domain.usecase

import kotlin.math.pow

class BinToOctUseCase {

    fun execute(binValue: String): String {
        return convertBinToOct(binValue)
    }

    private fun convertBinToOct(value: String): String {
        convertBinToDec(value)
        return ""
    }

    private fun convertBinToDec(bin: String): String {
        var dec: Int = 0
        var index = 0
        val size = bin.length - 1
        for (it in bin) {
            dec += 2.0.pow(size - index).toInt() * it.digitToInt()
            index++
        }

        return dec.toString()
    }
}