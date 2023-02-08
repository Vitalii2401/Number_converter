package com.example.myapplication.ui.contract

import androidx.fragment.app.Fragment

fun Fragment.navigator(): FragmentNavigator {
    return requireActivity() as FragmentNavigator
}

interface FragmentNavigator {

    fun showSettingsScreen()

    fun goBack()
}