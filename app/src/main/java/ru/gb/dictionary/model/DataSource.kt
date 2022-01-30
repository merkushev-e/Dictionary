package ru.gb.dictionary.model

import io.reactivex.rxjava3.core.Observable
import ru.gb.dictionary.model.data.DataModel


interface DataSource<T> {
    suspend fun getData(word: String): T
}