package ru.gb.historyscreen.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import values.HistoryInteractor


class HistoryViewModel(private val interactor: HistoryInteractor): ViewModel()  {

    private val liveDataToObserve: MutableLiveData<ru.gb.model.AppState> = MutableLiveData()
    val liveData: LiveData<ru.gb.model.AppState> = liveDataToObserve

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

     fun getData(word: String, isOnline: Boolean) {
        liveDataToObserve.value = ru.gb.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        liveDataToObserve.postValue(interactor.getData(word, isOnline))
    }

     fun handleError(error: Throwable) {
        liveDataToObserve.postValue(ru.gb.model.AppState.Error(error))
    }

    override fun onCleared() {
        liveDataToObserve.value = ru.gb.model.AppState.Success(null)
        super.onCleared()
    }
}
