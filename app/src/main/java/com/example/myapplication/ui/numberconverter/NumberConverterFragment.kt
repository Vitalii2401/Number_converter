package com.example.myapplication.ui.numberconverter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNumberConverterBinding
import com.example.myapplication.utility.setAllOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class NumberConverterFragment : Fragment(R.layout.fragment_number_converter) {

    private lateinit var binding: FragmentNumberConverterBinding
    private val numberConverterViewModel by viewModel<NumberConverterViewModel>()
    private val clipboard by lazy { requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    /*-- Fragment --*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNumberConverterBinding.bind(view)

        binding.textAnswerBin.movementMethod = ScrollingMovementMethod()
        binding.textAnswerOct.movementMethod = ScrollingMovementMethod()
        binding.textAnswerDec.movementMethod = ScrollingMovementMethod()
        binding.textAnswerHex.movementMethod = ScrollingMovementMethod()


        handleKeyboardClicks()
        updateAnswers()
        selectMode()
        updateUiAccordingToMode()
        longClick()
    }

    private fun longClick() {
        with(binding) {
            textAnswerBin.setOnLongClickListener {
                copyToClipboard((it as TextView).text.toString())
                true
            }

            textAnswerOct.setOnLongClickListener {
                copyToClipboard((it as TextView).text.toString())
                true
            }

            textAnswerDec.setOnLongClickListener {
                copyToClipboard((it as TextView).text.toString())
                true
            }

            textAnswerHex.setOnLongClickListener {
                copyToClipboard((it as TextView).text.toString())
                true
            }
        }
    }

    private fun copyToClipboard(text: String) {
        clipboard.setPrimaryClip(ClipData.newPlainText(RESULT, text))

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S)
            Toast.makeText(requireContext(), "Text $text copied to clipboard", Toast.LENGTH_SHORT)
                .show()
    }

    /*-- Options menu --*/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.settingsFragment)
        return true
    }

    /*-- Custom fun --*/
    private fun handleKeyboardClicks() {
        with(binding) {

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
            btnDot.setOnClickListener { numberConverterViewModel.addDot() }
            btnAc.setOnClickListener { numberConverterViewModel.deleteAllValue() }
        }
    }

    private fun updateAnswers() {
        numberConverterViewModel.bin.observe(viewLifecycleOwner) {
            binding.textAnswerBin.text = it.ifEmpty { "0" }
            binding.textAnswerBin.scrollX = getScrollValue(binding.textAnswerBin)
        }

        numberConverterViewModel.oct.observe(viewLifecycleOwner) {
            binding.textAnswerOct.text = it.ifEmpty { "0" }
            binding.textAnswerOct.scrollX = getScrollValue(binding.textAnswerOct)
        }

        numberConverterViewModel.dec.observe(viewLifecycleOwner) {
            binding.textAnswerDec.text = it.ifEmpty { "0" }
            binding.textAnswerDec.scrollX = getScrollValue(binding.textAnswerDec)
        }

        numberConverterViewModel.hex.observe(viewLifecycleOwner) {
            binding.textAnswerHex.text = it.ifEmpty { "0" }
            binding.textAnswerHex.scrollX = getScrollValue(binding.textAnswerHex)
        }

        numberConverterViewModel.isFull.observe(viewLifecycleOwner) {
            if (it)
                Toast.makeText(requireContext(), "Digit limit reached", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getScrollValue(textView: TextView): Int {
        val widthText = textView.paint.measureText(textView.text.toString()).toInt()
        val widthView = textView.width
        return if (widthView > widthText || widthView == 0) 0 else widthText - widthView
    }

    private fun selectMode() {
        with(binding) {
            groupModeBin.setAllOnClickListener {
                numberConverterViewModel.mode = MODE_BIN
                updateUiAccordingToMode()
            }

            groupModeOct.setAllOnClickListener {
                numberConverterViewModel.mode = MODE_OCT
                updateUiAccordingToMode()
            }

            groupModeDec.setAllOnClickListener {
                numberConverterViewModel.mode = MODE_DEC
                updateUiAccordingToMode()
            }

            groupModeHex.setAllOnClickListener {
                numberConverterViewModel.mode = MODE_HEX
                updateUiAccordingToMode()
            }
        }
    }

    private fun updateUiAccordingToMode() {
        resetTextStyleToDefault()

        when (numberConverterViewModel.mode) {
            MODE_BIN -> {
                with(binding) {
                    changeModeIndicatorParams(textBin)
                    changeTextStyleToAccented(textBin, textAnswerBin)

                    changeButtonsClickableOnKeyboard(groupOct, false, R.style.buttonNumberInactive)
                    changeButtonsClickableOnKeyboard(groupDec, false, R.style.buttonNumberInactive)
                    changeButtonsClickableOnKeyboard(groupHex, false, R.style.buttonLetterInactive)
                }
            }

            MODE_OCT -> {
                with(binding) {
                    changeModeIndicatorParams(textOct)
                    changeTextStyleToAccented(textOct, textAnswerOct)

                    changeButtonsClickableOnKeyboard(groupOct, true, R.style.buttonNumber)
                    changeButtonsClickableOnKeyboard(groupDec, false, R.style.buttonNumberInactive)
                    changeButtonsClickableOnKeyboard(groupHex, false, R.style.buttonLetterInactive)
                }
            }

            MODE_DEC -> {
                with(binding) {
                    changeModeIndicatorParams(textDec)
                    changeTextStyleToAccented(textDec, textAnswerDec)

                    changeButtonsClickableOnKeyboard(groupOct, true, R.style.buttonNumber)
                    changeButtonsClickableOnKeyboard(groupDec, true, R.style.buttonNumber)
                    changeButtonsClickableOnKeyboard(groupHex, false, R.style.buttonLetterInactive)
                }
            }

            MODE_HEX -> {
                with(binding) {
                    changeModeIndicatorParams(textHex)
                    changeTextStyleToAccented(textHex, textAnswerHex)

                    changeButtonsClickableOnKeyboard(groupOct, true, R.style.buttonNumber)
                    changeButtonsClickableOnKeyboard(groupDec, true, R.style.buttonNumber)
                    changeButtonsClickableOnKeyboard(groupHex, true, R.style.buttonLetter)
                }
            }
        }
    }

    private fun resetTextStyleToDefault() {
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

    private fun changeTextStyleToAccented(textMode: TextView, textAnswer: TextView) {
        textMode.setTextAppearance(R.style.textAccentNumberSystem)
        textAnswer.setTextAppearance(R.style.textAccentAnswer)
    }

    private fun changeModeIndicatorParams(connectedTextView: TextView) {
        val modeIndicatorParams =
            binding.modeIndicator.layoutParams as ConstraintLayout.LayoutParams

        modeIndicatorParams.topToTop = connectedTextView.id
        modeIndicatorParams.bottomToBottom = connectedTextView.id

        binding.modeIndicator.requestLayout()
    }

    private fun changeButtonsClickableOnKeyboard(
        groupButtons: Group,
        isClickable: Boolean,
        style: Int
    ) {
        groupButtons.referencedIds.forEach { id ->
            with(view?.findViewById<Button>(id)) {
                this?.isClickable = isClickable
                this?.setTextAppearance(style)
            }
        }
    }

    companion object {
        const val MODE_BIN = "bin"
        const val MODE_OCT = "oct"
        const val MODE_DEC = "dec"
        const val MODE_HEX = "hex"

        const val RESULT = "result"
    }
}