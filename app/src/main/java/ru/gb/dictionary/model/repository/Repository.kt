package ru.gb.dictionary.model.repository

import io.reactivex.rxjava3.core.Observable


interface Repository<T> {
    suspend fun getData(word: String): T
}
