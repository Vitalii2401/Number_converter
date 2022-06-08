package com.vetall24.myapplication.ui.numberconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
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

            btnBackspace.setOnClickListener { numberConverterViewModel.deleteValue() }
            btnAc.setOnClickListener { numberConverterViewModel.deleteAllValue() }
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
            textBin.setOnClickListener {
                numberConverterViewModel.changeMode(MODE_BIN)
                changeUiAccordingToMode(MODE_BIN)
            }
            textOct.setOnClickListener {
                numberConverterViewModel.changeMode(MODE_OCT)
                changeUiAccordingToMode(MODE_OCT)
            }
            textDec.setOnClickListener {
                numberConverterViewModel.changeMode(MODE_DEC)
                changeUiAccordingToMode(MODE_DEC)
            }
            textHex.setOnClickListener {
                numberConverterViewModel.changeMode(MODE_HEX)
                changeUiAccordingToMode(MODE_HEX)
            }
        }
    }

    private fun changeUiAccordingToMode(mode: String) {
        val modeIndicatorParams = binding.modeIndicator.layoutParams as ConstraintLayout.LayoutParams
        defaultState()
        when (mode) {
            MODE_BIN -> {
                with(binding) {
                    textAnswerBin.setTextAppearance(R.style.accentTextAnswer)
                    textBin.setTextAppearance(R.style.accentTextNumberSystem)
                    modeIndicatorParams.topToTop = textBin.id
                    modeIndicatorParams.bottomToBottom = textBin.id
                    modeIndicator.requestLayout()
                }
            }

            MODE_OCT -> {
                with(binding) {
                    textAnswerOct.setTextAppearance(R.style.accentTextAnswer)
                    textOct.setTextAppearance(R.style.accentTextNumberSystem)
                    modeIndicatorParams.topToTop = textOct.id
                    modeIndicatorParams.bottomToBottom = textOct.id
                    modeIndicator.requestLayout()
                }
            }

            MODE_DEC -> {
                with(binding) {
                    textAnswerDec.setTextAppearance(R.style.accentTextAnswer)
                    textDec.setTextAppearance(R.style.accentTextNumberSystem)
                    modeIndicatorParams.topToTop = textDec.id
                    modeIndicatorParams.bottomToBottom = textDec.id
                    modeIndicator.requestLayout()
                }
            }

            MODE_HEX -> {
                with(binding) {
                    textAnswerHex.setTextAppearance(R.style.accentTextAnswer)
                    textHex.setTextAppearance(R.style.accentTextNumberSystem)
                    modeIndicatorParams.topToTop = textHex.id
                    modeIndicatorParams.bottomToBottom = textHex.id
                    modeIndicator.requestLayout()
                }
            }
        }
    }

    private fun defaultState() {
        with(binding) {
            textBin.setTextAppearance(R.style.textNumberSystem)
            textOct.setTextAppearance(R.style.textNumberSystem)
            textDec.setTextAppearance(R.style.textNumberSystem)
            textHex.setTextAppearance(R.style.textNumberSystem)

            textAnswerBin.setTextAppearance(R.style.textAnswer)
            textAnswerOct.setTextAppearance(R.style.textAnswer)
            textAnswerDec.setTextAppearance(R.style.textAnswer)
            textAnswerHex.setTextAppearance(R.style.textAnswer)
        }
    }

    companion object {
        const val MODE_BIN = "bin"
        const val MODE_OCT = "oct"
        const val MODE_DEC = "dec"
        const val MODE_HEX = "hex"
    }
}