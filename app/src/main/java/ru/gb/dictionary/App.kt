package ru.gb.dictionary

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


import ru.gb.dictionary.DI.application
import ru.gb.dictionary.DI.mainScreen


import ru.gb.dictionary.DI.historyScreen


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }


}
