package ru.gb.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.gb.model.AppState
import ru.gb.dictionary.presenter.MainInteract


class MainViewModel (
    private val interactor: MainInteract
    ) : ViewModel() {


    private val liveDataToObserve: MutableLiveData<ru.gb.model.AppState> = MutableLiveData()
    val liveData: LiveData<ru.gb.model.AppState> = liveDataToObserve


    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })


    override fun onCleared() {
        liveDataToObserve.value = ru.gb.model.AppState.Success(null)
        cancelJob()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }


    fun getData(word: String, isOnline: Boolean) {
        liveDataToObserve.value = ru.gb.model.AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
            liveDataToObserve.postValue(interactor.getData(word, isOnline)) }
            }



        }

    fun handleError(error: Throwable) {
        liveDataToObserve.postValue(ru.gb.model.AppState.Error(error))
    }
}