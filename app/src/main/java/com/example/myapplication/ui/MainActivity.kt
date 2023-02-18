package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.contract.FragmentNavigator
import com.example.myapplication.ui.contract.HasCustomMenu
import com.example.myapplication.ui.contract.HasCustomTitle
import com.example.myapplication.ui.numberconverter.NumberConverterFragment
import com.example.myapplication.ui.settings.SettingsFragment
import com.example.myapplication.ui.settings.SettingsFragment.OnSettingsChanged
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), FragmentNavigator, OnSettingsChanged {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }

    private val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)

    private val mainViewModel by viewModel<MainViewModel>()

    /* Activity */
    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, NumberConverterFragment.newInstance())
                .commit()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    /* OnSettingsChanged */
    override fun applyThemeChange() {
        recreate()
    }

    override fun applyLanguageChange() {
        val locale = LocaleListCompat.forLanguageTags(mainViewModel.currentLanguage)
        AppCompatDelegate.setApplicationLocales(locale)
    }

    override fun applyNightModeChange() {
        AppCompatDelegate.setDefaultNightMode(mainViewModel.currentNightModeMask)
    }

    /* FragmentNavigator */
    override fun showSettingsScreen() {
        launchFragment(SettingsFragment.newInstance())
    }

    override fun goBack() {
        onSupportNavigateUp()
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    /* Setup app theme */
    private fun setAppTheme() {
        when (mainViewModel.currentTheme) {
            SettingsFragment.THEME_ORANGE -> setTheme(R.style.Theme_NumberConverterOrange)
            SettingsFragment.THEME_BLUE -> setTheme(R.style.Theme_NumberConverterBlue)
            SettingsFragment.THEME_VIOLET -> setTheme(R.style.Theme_NumberConverterPurple)
            else -> setTheme(R.style.Theme_NumberConverterGreen)
        }
    }

    /* Update action bar */
    private fun updateUi() {
        val fragment = currentFragment

        val isShowUpAction = supportFragmentManager.backStackEntryCount > 0
        val actionBarTitle =
            if (fragment is HasCustomTitle) getString(fragment.getCustomTitle()) else getString(R.string.app_name)

        supportActionBar?.apply {
            title = actionBarTitle
            setDisplayHomeAsUpEnabled(isShowUpAction)
        }

        if (fragment is HasCustomMenu) {
            with(fragment.getCustomMenu()) {
                addMenuProvider(provider, owner, state)
            }
        }
    }
}