package com.vetall24.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vetall24.myapplication.R
import com.vetall24.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}