package ru.gb.dictionary.model.room


import ru.gb.dictionary.AppState
import ru.gb.dictionary.model.DataSource


interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}