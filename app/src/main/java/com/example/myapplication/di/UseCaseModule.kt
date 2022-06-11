package com.example.myapplication.di

import com.example.myapplication.domain.usecase.BinToDecUseCase
import com.example.myapplication.domain.usecase.BinToHexUseCase
import com.example.myapplication.domain.usecase.BinToOctUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<BinToOctUseCase> { BinToOctUseCase() }
    factory<BinToDecUseCase> { BinToDecUseCase() }
    factory<BinToHexUseCase> { BinToHexUseCase() }
}