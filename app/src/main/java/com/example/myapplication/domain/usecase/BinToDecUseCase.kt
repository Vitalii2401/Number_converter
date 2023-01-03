package com.example.myapplication.domain.usecase

class BinToDecUseCase(
    private val convertToDecUseCase: ConvertToDecUseCase
) {

    fun execute(binValue: String): String {
        return convertToDecUseCase.execute(binValue, 2)
    }

 /*   private fun convertBinToDec(bin: String): String {
        var decResult = 0
        var index = 0
        val size = bin.length - 1

        for (it in bin) {
            decResult += 2.0.pow(size - index).toInt() * it.digitToInt()
            index++
        }

        return decResult.toString()
    }*/
}