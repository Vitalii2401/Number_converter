package com.example.myapplication.domain.repository

interface AppRepository {

    fun getAppLanguage(): String

    fun getAppTheme(): String

    fun getNightModeMask(): Int

    fun changeAppLanguage(newLanguage: String)

    fun changeAppTheme(newTheme: String)

    fun changeNightModeMask(newNightModeMask: Int)
}