package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.settings.SettingsFragment.OnSettingsChanged
import com.example.myapplication.utility.LocaleService
import com.example.myapplication.utility.THEME_BLUE
import com.example.myapplication.utility.THEME_ORANGE
import com.example.myapplication.utility.THEME_VIOLET
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnSettingsChanged {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppSettings()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNav()
    }

    override fun applySettingsChanges() {
        recreate()
    }

    private fun setAppSettings() {

        val appLocale: LocaleListCompat =
            LocaleListCompat.forLanguageTags(mainViewModel.currentLanguage)
        AppCompatDelegate.setApplicationLocales(appLocale)

        AppCompatDelegate.setDefaultNightMode(mainViewModel.currentNightModeMask)

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

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}