package ru.gb.dictionary.DI

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.gb.dictionary.view.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}