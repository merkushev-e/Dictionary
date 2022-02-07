package ru.gb.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.gb.dictionary.AppState
import ru.gb.dictionary.presenter.HistoryInteractor


class HistoryViewModel(private val interactor: HistoryInteractor): ViewModel()  {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState> = liveDataToObserve

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
        liveDataToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        liveDataToObserve.postValue(interactor.getData(word, isOnline))
    }

     fun handleError(error: Throwable) {
        liveDataToObserve.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveDataToObserve.value = AppState.Success(null)
        super.onCleared()
    }
}
