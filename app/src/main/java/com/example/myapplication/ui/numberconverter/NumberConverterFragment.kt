package com.example.myapplication.ui.numberconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNumberConverterBinding

class NumberConverterFragment : Fragment(R.layout.fragment_number_converter) {

    private lateinit var binding: FragmentNumberConverterBinding
    private val numberConverterViewModel by viewModels<NumberConverterViewModel>()
    private var currentMode = MODE_BIN

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNumberConverterBinding.bind(view)

        handleClicks()
        updateUi()
        selectMode()
        changeUiAccordingToMode()
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
                currentMode = MODE_BIN
                numberConverterViewModel.changeMode(currentMode)
                changeUiAccordingToMode()
            }
            textOct.setOnClickListener {
                currentMode = MODE_OCT
                numberConverterViewModel.changeMode(currentMode)
                changeUiAccordingToMode()
            }
            textDec.setOnClickListener {
                currentMode = MODE_DEC
                numberConverterViewModel.changeMode(currentMode)
                changeUiAccordingToMode()
            }
            textHex.setOnClickListener {
                currentMode = MODE_HEX
                numberConverterViewModel.changeMode(currentMode)
                changeUiAccordingToMode()
            }
        }
    }

    private fun changeUiAccordingToMode() {
        val modeIndicatorParams = binding.modeIndicator.layoutParams as ConstraintLayout.LayoutParams

        noAccentState()

        when (currentMode) {
            MODE_BIN -> {
                with(binding) {
                    textAnswerBin.setTextAppearance(R.style.accentTextAnswer)
                    textBin.setTextAppearance(R.style.accentTextNumberSystem)
                    modeIndicatorParams.topToTop = textBin.id
                    modeIndicatorParams.bottomToBottom = textBin.id
                    modeIndicator.requestLayout()

                    groupOct.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = false
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_inactive_number))
                        }
                    }

                    groupDec.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = false
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_inactive_number))
                        }
                    }

                    groupHex.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = false
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_inactive_letter))
                        }
                    }
                }
            }

            MODE_OCT -> {
                with(binding) {
                    textAnswerOct.setTextAppearance(R.style.accentTextAnswer)
                    textOct.setTextAppearance(R.style.accentTextNumberSystem)
                    modeIndicatorParams.topToTop = textOct.id
                    modeIndicatorParams.bottomToBottom = textOct.id
                    modeIndicator.requestLayout()

                    groupOct.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = true
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_number))
                        }
                    }

                    groupDec.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = false
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_inactive_number))
                        }
                    }

                    groupHex.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = false
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_inactive_letter))
                        }
                    }
                }
            }

            MODE_DEC -> {
                with(binding) {
                    textAnswerDec.setTextAppearance(R.style.accentTextAnswer)
                    textDec.setTextAppearance(R.style.accentTextNumberSystem)
                    modeIndicatorParams.topToTop = textDec.id
                    modeIndicatorParams.bottomToBottom = textDec.id
                    modeIndicator.requestLayout()

                    groupOct.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = true
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_number))
                        }
                    }

                    groupDec.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = true
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_number))
                        }
                    }

                    groupHex.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = false
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_inactive_letter))
                        }
                    }
                }
            }

            MODE_HEX -> {
                with(binding) {
                    textAnswerHex.setTextAppearance(R.style.accentTextAnswer)
                    textHex.setTextAppearance(R.style.accentTextNumberSystem)
                    modeIndicatorParams.topToTop = textHex.id
                    modeIndicatorParams.bottomToBottom = textHex.id
                    modeIndicator.requestLayout()

                    groupOct.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = true
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_number))
                        }
                    }

                    groupDec.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = true
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_number))
                        }
                    }

                    groupHex.referencedIds.forEach { id ->
                        with(view?.findViewById<Button>(id)) {
                            this?.isClickable  = true
                            this?.setTextColor(ContextCompat.getColor(context, R.color.color_letter))
                        }
                    }
                }
            }
        }
    }

    private fun noAccentState() {
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