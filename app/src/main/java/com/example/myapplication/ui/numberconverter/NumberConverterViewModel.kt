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

    fun addValue(value: String) {
        when (mode) {
            "bin" -> {
                if(_bin.value.toString().isEmpty() && value == "0")
                    return
                else
                    _bin.value += value
            }
            "oct" -> {
                if(_oct.value.toString().isEmpty() && value == "0")
                    return
                else
                    _oct.value += value
            }
            "dec" -> {
                if(_dec.value.toString().isEmpty() && value == "0")
                    return
                else
                    _dec.value += value
            }
            "hex" -> {
                if(_hex.value.toString().isEmpty() && value == "0")
                    return
                else
                    _hex.value += value
            }
        }

        convert()
    }

    fun addDot() {
        if (!dotAdded) {
            when (mode) {
                "bin" -> {
                    if (_bin.value.toString().isEmpty())
                        return
                    else
                        _bin.value += "."
                }
                "oct" -> {
                    if (_oct.value.toString().isEmpty())
                        return
                    else
                        _oct.value += "."
                }
                "dec" -> {
                    if (_dec.value.toString().isEmpty())
                        return
                    else
                        _dec.value += "."
                }
                "hex" -> {
                    if (_hex.value.toString().isEmpty())
                        return
                    else
                        _hex.value += "."
                }
            }

            dotAdded = true
        }
    }

    fun deleteValue() {
        when (mode) {
            "bin" -> {
                if(_bin.value.toString().last() == '.')
                    dotAdded = false

                _bin.value = _bin.value.toString().dropLast(1)

                if(_bin.value.toString().isEmpty()) {
                    deleteAllValue()
                    return
                }
            }
            "oct" -> {
                if(_oct.value.toString().last() == '.')
                    dotAdded = false

                _oct.value = _oct.value.toString().dropLast(1)

                if(_oct.value.toString().isEmpty()) {
                    deleteAllValue()
                    return
                }
            }
            "dec" -> {
                if(_dec.value.toString().last() == '.')
                    dotAdded = false

                _dec.value = _dec.value.toString().dropLast(1)

                if(_dec.value.toString().isEmpty()) {
                    deleteAllValue()
                    return
                }
            }
            "hex" -> {
                if(_hex.value.toString().last() == '.')
                    dotAdded = false

                _hex.value = _hex.value.toString().dropLast(1)

                if(_hex.value.toString().isEmpty()) {
                    deleteAllValue()
                    return
                }
            }
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