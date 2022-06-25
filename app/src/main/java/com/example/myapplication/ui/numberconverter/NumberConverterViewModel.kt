package com.example.myapplication.ui.numberconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.*

class NumberConverterViewModel(
    private val binToOctUseCase: BinToOctUseCase,
    private val binToDecUseCase: BinToDecUseCase,
    private val binToHexUseCase: BinToHexUseCase,
    private val octToBinUseCase: OctToBinUseCase,
    private val octToDecUseCase: OctToDecUseCase,
    private val octToHexUseCase: OctToHexUseCase,
    private val decToBinUseCase: DecToBinUseCase,
    private val decToOctUseCase: DecToOctUseCase,
    private val decToHexUseCase: DecToHexUseCase,
    private val hexToBinUseCase: HexToBinUseCase,
    private val hexToOctUseCase: HexToOctUseCase,
    private val hexToDecUseCase: HexToDecUseCase,
) : ViewModel() {

    private var mode = NumberConverterFragment.MODE_BIN
    private var dotAdded = false

    private val _bin = MutableLiveData<String>()
    private val _oct = MutableLiveData<String>()
    private val _dec = MutableLiveData<String>()
    private val _hex = MutableLiveData<String>()

    val bin: LiveData<String> = _bin
    val oct: LiveData<String> = _oct
    val dec: LiveData<String> = _dec
    val hex: LiveData<String> = _hex

    private fun currentMode(): MutableLiveData<String> {
        return when (mode) {
            NumberConverterFragment.MODE_BIN -> _bin
            NumberConverterFragment.MODE_OCT -> _oct
            NumberConverterFragment.MODE_DEC -> _dec
            NumberConverterFragment.MODE_HEX -> _hex
            else -> {_bin}
        }
    }

    fun addValue(value: String) {
        if (currentMode().value.toString().isEmpty() && value == "0")
            return
        else
            currentMode().value += value

        convert()
    }

    fun addDot() {
        if (!dotAdded) {
            if (currentMode().value.toString().isEmpty())
                return
            else
                currentMode().value += "."

            dotAdded = true
        }
    }

    fun deleteValue() {
        if(currentMode().value.toString().isEmpty()) {
            deleteAllValue()
            return
        }

        if(currentMode().value.toString().last() == '.')
            dotAdded = false

        currentMode().value = currentMode().value.toString().dropLast(1)
        convert()
    }

    fun deleteAllValue() {
        _bin.value = ""
        _oct.value = ""
        _dec.value = ""
        _hex.value = ""

        dotAdded = false
    }

    fun changeMode(changedMode: String) {
        mode = changedMode
    }

    private fun convert() {
        when (mode) {
            "bin" -> {
                _oct.value = binToOctUseCase.execute(_bin.value.toString())
                _dec.value = binToDecUseCase.execute(_bin.value.toString())
                _hex.value = binToHexUseCase.execute(_bin.value.toString())
            }
            "oct" -> {
                _bin.value = octToBinUseCase.execute(_oct.value.toString())
                _dec.value = octToDecUseCase.execute(_oct.value.toString())
                _hex.value = octToHexUseCase.execute(_oct.value.toString())
            }
            "dec" -> {
                _bin.value = decToBinUseCase.execute(_dec.value.toString())
                _oct.value = decToOctUseCase.execute(_dec.value.toString())
                _hex.value = decToHexUseCase.execute(_dec.value.toString())
            }
            "hex" -> {
                _bin.value = hexToBinUseCase.execute(_hex.value.toString())
                _oct.value = hexToOctUseCase.execute(_hex.value.toString())
                _dec.value = hexToDecUseCase.execute(_hex.value.toString())
            }
        }
    }

    init {
        _bin.value = ""
        _oct.value = ""
        _dec.value = ""
        _hex.value = ""
    }
}