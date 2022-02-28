package ru.gb.repository.repository




interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: ru.gb.model.AppState)
}
