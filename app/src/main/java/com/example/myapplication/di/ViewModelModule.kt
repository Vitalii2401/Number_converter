package com.example.myapplication.di

import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.ui.numberconverter.NumberConverterViewModel
import com.example.myapplication.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        NumberConverterViewModel(
            binToOctUseCase = get(),
            binToDecUseCase = get(),
            binToHexUseCase = get(),
            octToBinUseCase = get(),
            octToDecUseCase = get(),
            octToHexUseCase = get(),
            decToBinUseCase = get(),
            decToOctUseCase = get(),
            decToHexUseCase = get(),
            hexToBinUseCase = get(),
            hexToOctUseCase = get(),
            hexToDecUseCase = get(),
        )
    }

    viewModel {
        MainViewModel(
            getAppThemeUseCase = get(),
            getAppNightModeMaskUseCase = get(),
            getAppLanguageUseCase = get()
        )
    }

    viewModel {
        SettingsViewModel(
            changeAppThemeUseCase = get(),
            changeAppLanguageUseCase = get(),
            changeAppNightModeMaskUseCase = get(),
            getAppLanguageUseCase = get(),
            getAppThemeUseCase = get()
        )
    }
}