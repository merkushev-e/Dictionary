package ru.gb.core



interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}