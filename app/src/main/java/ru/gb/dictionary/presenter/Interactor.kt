package ru.gb.dictionary.presenter

import io.reactivex.rxjava3.core.Observable


interface Interactor<T> {
    // Use Сase: получение данных для вывода на экран
    // Используем RxJava
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}