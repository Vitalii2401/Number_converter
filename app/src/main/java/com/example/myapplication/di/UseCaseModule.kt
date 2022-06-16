package com.example.myapplication.di

import com.example.myapplication.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory<ConvertToDecUseCase> { ConvertToDecUseCase() }
    factory<ConvertDecToUseCase> { ConvertDecToUseCase() }

    factory<BinToOctUseCase> { BinToOctUseCase(get(), get()) }
    factory<BinToDecUseCase> { BinToDecUseCase(get()) }
    factory<BinToHexUseCase> { BinToHexUseCase(get(), get()) }
    factory<OctToBinUseCase> { OctToBinUseCase() }
    factory<OctToDecUseCase> { OctToDecUseCase() }
    factory<OctToHexUseCase> { OctToHexUseCase() }
    factory<DecToBinUseCase> { DecToBinUseCase() }
    factory<DecToOctUseCase> { DecToOctUseCase() }
    factory<DecToHexUseCase> { DecToHexUseCase() }
    factory<HexToBinUseCase> { HexToBinUseCase() }
    factory<HexToOctUseCase> { HexToOctUseCase() }
    factory<HexToDecUseCase> { HexToDecUseCase() }
}