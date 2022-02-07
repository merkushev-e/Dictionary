package ru.gb.dictionary.model.repository


import ru.gb.dictionary.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
