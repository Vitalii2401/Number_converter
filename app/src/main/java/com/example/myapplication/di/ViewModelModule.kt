package com.example.myapplication.di

import com.example.myapplication.ui.numberconverter.NumberConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { NumberConverterViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get())}
}