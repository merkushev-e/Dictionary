package ru.gb.repository.repository


import ru.gb.model.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: ru.gb.model.AppState)
}
