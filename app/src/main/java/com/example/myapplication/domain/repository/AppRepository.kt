package com.example.myapplication.domain.repository

interface AppRepository {

    fun getAppLanguage(): String

    fun getAppTheme(): String

    fun getNightModeMask(): Int
}