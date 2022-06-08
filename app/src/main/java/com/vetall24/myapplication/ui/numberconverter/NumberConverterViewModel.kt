package com.vetall24.myapplication.ui.numberconverter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vetall24.myapplication.R

class NumberConverterViewModel : ViewModel() {

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
            "bin" -> _bin.value += value
            "oct" -> _oct.value += value
            "dec" -> _dec.value += value
            "hex" -> _hex.value += value
        }
    }

    fun deleteValue() {
        when (mode) {
            "bin" -> _bin.value = _bin.value.toString().dropLast(1)
            "oct" -> _oct.value = _oct.value.toString().dropLast(1)
            "dec" -> _dec.value = _dec.value.toString().dropLast(1)
            "hex" -> _hex.value = _hex.value.toString().dropLast(1)
        }
    }

    fun deleteAllValue() {
        when (mode) {
            "bin" -> _bin.value = ""
            "oct" -> _oct.value = ""
            "dec" -> _dec.value = ""
            "hex" -> _hex.value = ""
        }
    }

    fun changeMode(changedMode: String) {
        mode = changedMode
    }

    init {
        _bin.value = ""
        _oct.value = ""
        _dec.value = ""
        _hex.value = ""
    }

}