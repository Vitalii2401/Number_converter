package com.vetall24.myapplication.ui.numberconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumberConverterViewModel : ViewModel() {

    private val _bin = MutableLiveData<String>()
    private val _oct = MutableLiveData<String>()
    private val _dec = MutableLiveData<String>()
    private val _hex = MutableLiveData<String>()

    val bin: LiveData<String> = _bin
    val oct: LiveData<String> = _oct
    val dec: LiveData<String> = _dec
    val hex: LiveData<String> = _hex

}