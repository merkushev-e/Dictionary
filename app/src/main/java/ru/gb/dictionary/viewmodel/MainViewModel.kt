package ru.gb.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.gb.model.AppState
import ru.gb.dictionary.presenter.MainInteract

class MainViewModel(
    private val interactor: MainInteract,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState> = liveDataToObserve


    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        handleError(throwable)
    }

    override fun onCleared() {
        liveDataToObserve.value = AppState.Success(null)
    }


    fun getData(word: String, isOnline: Boolean) {
        liveDataToObserve.value = AppState.Loading(null)

        viewModelScope.launch(defaultDispatcher + coroutineExceptionHandler) {
                liveDataToObserve.postValue(interactor.getData(word, isOnline))
        }
    }

    private fun handleError(error: Throwable) {
        liveDataToObserve.postValue(AppState.Error(error))
    }
}