package com.example.myapplication.ui.settings

import android.content.Intent
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.ui.MainActivity
import java.util.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        checkedRadioGroup()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        changeLanguage()
    }

    private fun checkedRadioGroup() {
        when(Locale.getDefault().language){
            Locale.ENGLISH.language -> binding.radioGroupLanguage.check(R.id.settings_language_en)
            Locale.UK.language -> binding.radioGroupLanguage.check(R.id.settings_language_uk)
        }
        Log.d("language", "updateUi: ${Locale.getDefault().language}\n -> ${Locale.ENGLISH.language}")
    }

    private fun changeLanguage() {
        binding.radioGroupLanguage.setOnCheckedChangeListener { radioGroup, button ->
            when(button) {
                R.id.settings_language_en -> changeLocalization("en")
                R.id.settings_language_uk -> changeLocalization("uk")
            }
        }


    }

    private fun changeLocalization(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)



    }
}