package ru.gb.repository.repository



interface Repository<T> {
    suspend fun getData(word: String): T
}
