package ru.gb.dictionary

import android.app.Application

import geekbrains.ru.translator.di.application
import geekbrains.ru.translator.di.mainScreen
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
