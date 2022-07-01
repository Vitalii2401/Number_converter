package com.example.myapplication.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
    }
}