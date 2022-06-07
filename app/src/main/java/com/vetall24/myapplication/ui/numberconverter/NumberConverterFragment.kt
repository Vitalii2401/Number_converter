package com.vetall24.myapplication.ui.numberconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.vetall24.myapplication.R
import com.vetall24.myapplication.databinding.FragmentNumberConverterBinding

class NumberConverterFragment : Fragment(R.layout.fragment_number_converter) {

    private lateinit var binding: FragmentNumberConverterBinding
    private val numberConverterViewModel by viewModels<NumberConverterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNumberConverterBinding.bind(view)

        handleClicks()
        updateUi()
        selectMode()
    }


    private fun handleClicks() {
        with(binding) {

            btnDot.setOnClickListener { numberConverterViewModel.addValue(".") }
            btn0.setOnClickListener { numberConverterViewModel.addValue("0") }
            btn1.setOnClickListener { numberConverterViewModel.addValue("1") }
            btn2.setOnClickListener { numberConverterViewModel.addValue("2") }
            btn3.setOnClickListener { numberConverterViewModel.addValue("3") }
            btn4.setOnClickListener { numberConverterViewModel.addValue("4") }
            btn5.setOnClickListener { numberConverterViewModel.addValue("5") }
            btn6.setOnClickListener { numberConverterViewModel.addValue("6") }
            btn7.setOnClickListener { numberConverterViewModel.addValue("7") }
            btn8.setOnClickListener { numberConverterViewModel.addValue("8") }
            btn9.setOnClickListener { numberConverterViewModel.addValue("9") }
            btnA.setOnClickListener { numberConverterViewModel.addValue("A") }
            btnB.setOnClickListener { numberConverterViewModel.addValue("B") }
            btnC.setOnClickListener { numberConverterViewModel.addValue("C") }
            btnD.setOnClickListener { numberConverterViewModel.addValue("D") }
            btnE.setOnClickListener { numberConverterViewModel.addValue("E") }
            btnF.setOnClickListener { numberConverterViewModel.addValue("F") }

            btnBackspace.setOnClickListener {
                // TODO
            }

            btnAc.setOnClickListener {
                // TODO
            }
        }
    }

    private fun updateUi() {
        numberConverterViewModel.bin.observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.textAnswerBin.text = "0"
            else
                binding.textAnswerBin.text = it
        }

        numberConverterViewModel.oct.observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.textAnswerOct.text = "0"
            else
                binding.textAnswerOct.text = it
        }

        numberConverterViewModel.dec.observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.textAnswerDec.text = "0"
            else
                binding.textAnswerDec.text = it
        }

        numberConverterViewModel.hex.observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.textAnswerHex.text = "0"
            else
                binding.textAnswerHex.text = it
        }
    }

    private fun selectMode() {
        with(binding) {
            textBin.setOnClickListener { numberConverterViewModel.changeMode(MODE_BIN) }
            textOct.setOnClickListener { numberConverterViewModel.changeMode(MODE_OCT) }
            textDec.setOnClickListener { numberConverterViewModel.changeMode(MODE_DEC) }
            textHex.setOnClickListener { numberConverterViewModel.changeMode(MODE_HEX) }
        }
    }

    companion object {
        const val MODE_BIN = "bin"
        const val MODE_OCT = "oct"
        const val MODE_DEC = "dec"
        const val MODE_HEX = "hex"
    }
}