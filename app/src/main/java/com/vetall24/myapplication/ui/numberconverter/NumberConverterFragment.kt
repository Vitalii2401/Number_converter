package com.vetall24.myapplication.ui.numberconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.vetall24.myapplication.R
import com.vetall24.myapplication.databinding.FragmentNumberConverterBinding

class NumberConverterFragment : Fragment(R.layout.fragment_number_converter) {

    private lateinit var binding: FragmentNumberConverterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNumberConverterBinding.bind(view)

        handleClicks()
    }

    private fun handleClicks() {
        with(binding) {

            btn0.setOnClickListener {
                // TODO
             }

            btn1.setOnClickListener {
                // TODO
            }

            btn2.setOnClickListener {
                // TODO
            }

            btn3.setOnClickListener {
                // TODO
            }

            btn4.setOnClickListener {
                // TODO
            }

            btn5.setOnClickListener {
                // TODO
            }

            btn6.setOnClickListener {
                // TODO
            }

            btn7.setOnClickListener {
                // TODO
            }

            btn8.setOnClickListener {
                // TODO
            }

            btn9.setOnClickListener {
                // TODO
            }

            btnDot.setOnClickListener {
                // TODO
            }

            btnA.setOnClickListener {
                // TODO
            }

            btnB.setOnClickListener {
                // TODO
            }

            btnC.setOnClickListener {
                // TODO
            }

            btnD.setOnClickListener {
                // TODO
            }

            btnE.setOnClickListener {
                // TODO
            }

            btnF.setOnClickListener {
                // TODO
            }

            btnBackspace.setOnClickListener {
                // TODO
            }

            btnAc.setOnClickListener {
                // TODO
            }
        }
    }
}