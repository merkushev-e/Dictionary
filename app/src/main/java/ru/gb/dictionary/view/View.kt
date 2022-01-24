package ru.gb.dictionary.view

import ru.gb.dictionary.AppState

interface View {

    fun renderData(appState: AppState)
}