package ru.gb.repository



interface DataSource<T> {
    suspend fun getData(word: String): T
}