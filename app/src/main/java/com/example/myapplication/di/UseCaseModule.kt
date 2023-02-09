package com.example.myapplication.di

import com.example.myapplication.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory<ConvertToDecUseCase> { ConvertToDecUseCase() }
    factory<ConvertDecToUseCase> { ConvertDecToUseCase() }

    /* Binary */
    factory<BinToOctUseCase> {
        BinToOctUseCase(
            convertToDecUseCase = get(),
            convertDecToUseCase = get()
        )
    }
    factory<BinToDecUseCase> { BinToDecUseCase(convertToDecUseCase = get()) }
    factory<BinToHexUseCase> {
        BinToHexUseCase(
            convertToDecUseCase = get(),
            convertDecToUseCase = get()
        )
    }

    /* Octal */
    factory<OctToBinUseCase> {
        OctToBinUseCase(
            convertToDecUseCase = get(),
            convertDecToUseCase = get()
        )
    }
    factory<OctToDecUseCase> { OctToDecUseCase(convertToDecUseCase = get()) }
    factory<OctToHexUseCase> {
        OctToHexUseCase(
            convertToDecUseCase = get(),
            convertDecToUseCase = get()
        )
    }

    /* Decimal */
    factory<DecToBinUseCase> { DecToBinUseCase(convertDecToUseCase = get()) }
    factory<DecToOctUseCase> { DecToOctUseCase(convertDecToUseCase = get()) }
    factory<DecToHexUseCase> { DecToHexUseCase(convertDecToUseCase = get()) }

    /* Hexadecimal */
    factory<HexToBinUseCase> {
        HexToBinUseCase(
            convertToDecUseCase = get(),
            convertDecToUseCase = get()
        )
    }
    factory<HexToDecUseCase> { HexToDecUseCase(convertToDecUseCase = get()) }
    factory<HexToOctUseCase> {
        HexToOctUseCase(
            convertToDecUseCase = get(),
            convertDecToUseCase = get()
        )
    }

    /* Get app settings */
    factory<GetAppThemeUseCase> { GetAppThemeUseCase(repository = get()) }
    factory<GetAppNightModeMaskUseCase> { GetAppNightModeMaskUseCase(repository = get()) }
    factory<GetAppLanguageUseCase> { GetAppLanguageUseCase(repository = get()) }

    /* Change app settings */
    factory<ChangeAppThemeUseCase> { ChangeAppThemeUseCase(repository = get()) }
    factory<ChangeAppLanguageUseCase> { ChangeAppLanguageUseCase(repository = get()) }
    factory<ChangeAppNightModeMaskUseCase> { ChangeAppNightModeMaskUseCase(repository = get()) }
}