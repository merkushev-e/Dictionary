package ru.gb.dictionary.presenter


import ru.gb.dictionary.AppState
import ru.gb.dictionary.view.View


interface Presenter<T: AppState, V: View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
