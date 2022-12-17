package com.example.myapplication.domain.usecase

import android.util.Log
import kotlin.math.pow

class BinToHexUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase,
    private val convertDecToUseCase: ConvertDecToUseCase
) {

    fun execute(binValue: String): String {
        val res = convertDecToUseCase.execute(
            convertToDecUseCase.execute(binValue, 2),
            16,
            256
        )
        Log.d("Test", "execute convert to dec -> ${convertToDecUseCase.execute(binValue, 2)} ")
        Log.d("Test", "execute result -> ${res}")
        return res
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