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

    private val _bin = MutableLiveData<String>()
    private val _oct = MutableLiveData<String>()
    private val _dec = MutableLiveData<String>()
    private val _hex = MutableLiveData<String>()

    val bin: LiveData<String> = _bin
    val oct: LiveData<String> = _oct
    val dec: LiveData<String> = _dec
    val hex: LiveData<String> = _hex

    private var dotAdded = false

    var mode = NumberConverterFragment.MODE_BIN

    init {
        _bin.value = ""
        _oct.value = ""
        _dec.value = ""
        _hex.value = ""
    }

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
        if (!dotAdded && currentMode().value.toString().isNotEmpty()) {
            _bin.value += "."
            _oct.value += "."
            _dec.value += "."
            _hex.value += "."

            dotAdded = true
        }
    }

    fun deleteValue() {
        currentMode().value = currentMode().value.toString().dropLast(1)

        if(currentMode().value.toString().isEmpty()) {
            deleteAllValue()
            return
        }

        if(!currentMode().value.toString().contains(".")){
            dotAdded = false
        } else if (currentMode().value.toString().last() == '.') {
            _bin.value = _bin.value.toString().substringBefore(".") + "."
            _oct.value = _oct.value.toString().substringBefore(".") + "."
            _dec.value = _dec.value.toString().substringBefore(".") + "."
            _hex.value = _hex.value.toString().substringBefore(".") + "."

            return
        }

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
            NumberConverterFragment.MODE_BIN -> {
                _oct.value = binToOctUseCase.execute(_bin.value.toString())
                _dec.value = binToDecUseCase.execute(_bin.value.toString())
                _hex.value = binToHexUseCase.execute(_bin.value.toString())
            }
            NumberConverterFragment.MODE_OCT -> {
                _bin.value = octToBinUseCase.execute(_oct.value.toString())
                _dec.value = octToDecUseCase.execute(_oct.value.toString())
                _hex.value = octToHexUseCase.execute(_oct.value.toString())
            }
            NumberConverterFragment.MODE_DEC -> {
                _bin.value = decToBinUseCase.execute(_dec.value.toString())
                _oct.value = decToOctUseCase.execute(_dec.value.toString())
                _hex.value = decToHexUseCase.execute(_dec.value.toString())
            }
            NumberConverterFragment.MODE_HEX -> {
                _bin.value = hexToBinUseCase.execute(_hex.value.toString())
                _oct.value = hexToOctUseCase.execute(_hex.value.toString())
                _dec.value = hexToDecUseCase.execute(_hex.value.toString())
            }
        }
    }
}