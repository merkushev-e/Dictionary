package ru.gb.repository.room


import ru.gb.model.AppState
import ru.gb.repository.DataSource


interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: ru.gb.model.AppState)
}