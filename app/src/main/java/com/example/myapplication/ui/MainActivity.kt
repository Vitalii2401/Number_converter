package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utility.LocaleService
import com.example.myapplication.utility.THEME_BLUE
import com.example.myapplication.utility.THEME_ORANGE
import com.example.myapplication.utility.THEME_VIOLET
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme()
        LocaleService.updateAppTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNav()
    }

    private fun setAppTheme() {
        when (mainViewModel.currentTheme) {
            THEME_ORANGE -> setTheme(R.style.Theme_NumberConverterOrange)
            THEME_BLUE -> setTheme(R.style.Theme_NumberConverterBlue)
            THEME_VIOLET -> setTheme(R.style.Theme_NumberConverterPurple)
            else -> setTheme(R.style.Theme_NumberConverterGreen)
        }
    }

    private fun initNav() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { LocaleService.updateBaseContextLocale(it) })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}