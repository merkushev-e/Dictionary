package ru.gb.dictionary.presenter

import io.reactivex.rxjava3.core.Observable


interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}