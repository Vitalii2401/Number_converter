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
    private val _isDigitLimit = MutableLiveData<Boolean>()

    val bin: LiveData<String> = _bin
    val oct: LiveData<String> = _oct
    val dec: LiveData<String> = _dec
    val hex: LiveData<String> = _hex
    val isDigitLimit: LiveData<Boolean> = _isDigitLimit

    private var isDotAdded = false
    var mode = NumberConverterFragment.MODE_BIN

    init {
        _bin.value = ""
        _oct.value = ""
        _dec.value = ""
        _hex.value = ""
        _isDigitLimit.value = false
    }

    /* Add value */
    fun addValue(value: String) {
        if (checkDigitLimit()) {
            return
        }

        if (getCurrentValue().isEmpty() && value == "0") {
            return
        }

        getCurrentMode().value += value
        convert()
    }

    fun addDot() {
        if (isDotAdded || getCurrentValue().isEmpty())
            return

        _bin.value += "."
        _oct.value += "."
        _dec.value += "."
        _hex.value += "."

        isDotAdded = true
    }

    /* Delete value */
    fun deleteValue() {
        getCurrentMode().value = getCurrentValue().dropLast(1)

        if (getCurrentValue().isEmpty()) {
            deleteAllValue()
            return
        }

        if (!getCurrentValue().contains(".")) {
            isDotAdded = false
        } else if (getCurrentValue().last() == '.') {
            _bin.value = _bin.value.toString().substringBefore(".") + "."
            _oct.value = _oct.value.toString().substringBefore(".") + "."
            _dec.value = _dec.value.toString().substringBefore(".") + "."
            _hex.value = _hex.value.toString().substringBefore(".") + "."

            return
        }

        convert()
        checkDigitLimit()
    }

    fun deleteAllValue() {
        _bin.value = ""
        _oct.value = ""
        _dec.value = ""
        _hex.value = ""

        isDotAdded = false
        checkDigitLimit()
    }

    private fun checkDigitLimit(): Boolean {
        _isDigitLimit.value = (_hex.value?.length ?: 0) + 1 >= 64
        return _isDigitLimit.value as Boolean
    }

    /* Get value */
    private fun getCurrentMode(): MutableLiveData<String> = when (mode) {
        NumberConverterFragment.MODE_OCT -> _oct
        NumberConverterFragment.MODE_DEC -> _dec
        NumberConverterFragment.MODE_HEX -> _hex
        else -> _bin
    }

    private fun getCurrentValue(): String = getCurrentMode().value.toString()

    /* Convert */
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
                _bin.value = decToBinUseCase.execute(_dec.value.toString(), PRECISION)
                _oct.value = decToOctUseCase.execute(_dec.value.toString(), PRECISION)
                _hex.value = decToHexUseCase.execute(_dec.value.toString(), PRECISION)
            }
            NumberConverterFragment.MODE_HEX -> {
                _bin.value = hexToBinUseCase.execute(_hex.value.toString())
                _oct.value = hexToOctUseCase.execute(_hex.value.toString())
                _dec.value = hexToDecUseCase.execute(_hex.value.toString())
            }
        }
    }

    companion object {
        private const val PRECISION = 10
    }
}