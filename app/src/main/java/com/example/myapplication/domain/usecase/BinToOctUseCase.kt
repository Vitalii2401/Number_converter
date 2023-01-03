package com.example.myapplication.domain.usecase

import kotlin.math.pow

class BinToOctUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase,
    private val convertDecToUseCase: ConvertDecToUseCase
)  {

    fun execute(binValue: String): String {
        return convertDecToUseCase.execute(
            convertToDecUseCase.execute(binValue, 2),
            8,
            256
        )
    }

    /*private fun convertBinToOct(value: String): String {
        var octResult = ""
        var dec = convertBinToDec(value)

        while (dec >= 8) {
            octResult += (dec % 8).toString()
            dec /= 8
        }
        octResult += dec.toString()
        octResult = octResult.reversed()

        return octResult
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
    }*/
}