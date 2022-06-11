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
) : ViewModel() {

    private var mode = NumberConverterFragment.MODE_BIN

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
                _bin.value += value
                convert()
            }
            "oct" -> {
                _oct.value += value
                convert()
            }
            "dec" -> {
                _dec.value += value
                //convert()
            }
            "hex" -> {
                _hex.value += value
                //convert()
            }
        }
    }

    fun deleteValue() {
        when (mode) {
            "bin" -> {
                _bin.value = _bin.value.toString().dropLast(1)
                convert()
            }
            "oct" -> {
                _oct.value = _oct.value.toString().dropLast(1)
                convert()
            }
            "dec" -> {
                _dec.value = _dec.value.toString().dropLast(1)
                //convert()
            }
            "hex" -> {
                _hex.value = _hex.value.toString().dropLast(1)
                //convert()
            }
        }
    }

    fun deleteAllValue() {
        _bin.value = ""
        _oct.value = ""
        _dec.value = ""
        _hex.value = ""
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