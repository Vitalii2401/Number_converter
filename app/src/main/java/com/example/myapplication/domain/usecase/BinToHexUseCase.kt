package com.example.myapplication.domain.usecase

import kotlin.math.pow

class BinToHexUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase,
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(binValue: String): String {
        return convertDecToUseCase.execute(
            convertToDecUseCase.execute(binValue, 2),
            16
        )
    }

   /* private fun convertBinToDec(bin: String): Int {
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