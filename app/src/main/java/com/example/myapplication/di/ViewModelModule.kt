package com.example.myapplication.di

import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.ui.numberconverter.NumberConverterViewModel
import com.example.myapplication.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        NumberConverterViewModel(
            get(), get(), get(),
            get(), get(), get(),
            get(), get(), get(),
            get(), get(), get(),
        )
    }

    viewModel {
        MainViewModel(
            get(), get(), get()
        )
    }

    viewModel {
        SettingsViewModel(
            get(), get(), get(),
            get(), get()
        )
    }
}