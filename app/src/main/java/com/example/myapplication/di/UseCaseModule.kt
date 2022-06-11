package com.example.myapplication.di

import com.example.myapplication.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory<BinToOctUseCase> { BinToOctUseCase() }
    factory<BinToDecUseCase> { BinToDecUseCase() }
    factory<BinToHexUseCase> { BinToHexUseCase() }
    factory<OctToBinUseCase> { OctToBinUseCase() }
    factory<OctToDecUseCase> { OctToDecUseCase() }
    factory<OctToHexUseCase> { OctToHexUseCase() }
}