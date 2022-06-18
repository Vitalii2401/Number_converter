package com.example.myapplication.di

import com.example.myapplication.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory<ConvertToDecUseCase> { ConvertToDecUseCase() }
    factory<ConvertDecToUseCase> { ConvertDecToUseCase() }

    factory<BinToOctUseCase> { BinToOctUseCase(get(), get()) }
    factory<BinToDecUseCase> { BinToDecUseCase(get()) }
    factory<BinToHexUseCase> { BinToHexUseCase(get(), get()) }

    factory<OctToBinUseCase> { OctToBinUseCase(get(), get()) }
    factory<OctToDecUseCase> { OctToDecUseCase(get()) }
    factory<OctToHexUseCase> { OctToHexUseCase(get(), get()) }

    factory<DecToBinUseCase> { DecToBinUseCase(get()) }
    factory<DecToOctUseCase> { DecToOctUseCase(get()) }
    factory<DecToHexUseCase> { DecToHexUseCase(get()) }

    factory<HexToBinUseCase> { HexToBinUseCase(get(), get()) }
    factory<HexToDecUseCase> { HexToDecUseCase(get()) }
    factory<HexToOctUseCase> { HexToOctUseCase(get(), get()) }
}