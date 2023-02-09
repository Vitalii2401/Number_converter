package com.example.myapplication.data.datasource

interface AppDataSource {

    interface Settings {

        fun getLanguage(): String

        fun getTheme(): String

        fun getNightModeMask(): Int

        fun changeAppLanguage(newLanguage: String)

        fun changeAppTheme(newTheme: String)

        fun changeNightModeMask(newNightModeMask: Int)
    }
}