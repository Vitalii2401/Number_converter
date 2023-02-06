package com.example.myapplication.domain.repository

interface NumberConverterRepository {

    fun getAppLanguage(): String

    fun getAppTheme(): String

    fun getNightModeMask(): Int
}