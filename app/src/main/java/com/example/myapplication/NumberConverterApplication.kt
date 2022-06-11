package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.useCaseModule
import org.koin.core.context.startKoin

class NumberConverterApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    useCaseModule
                )
            )
        }
    }
}