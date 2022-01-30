package ru.gb.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import ru.gb.dictionary.AppState
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.RepositoryImpl
import ru.gb.dictionary.presenter.MainInteract
import javax.inject.Inject

class MainViewModel (
    private val interactor: MainInteract,

    ) : ViewModel() {


    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState> = liveDataToObserve


    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })


    override fun onCleared() {
        liveDataToObserve.value = AppState.Success(null)
        cancelJob()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }


    fun getData(word: String, isOnline: Boolean) {
        liveDataToObserve.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
            liveDataToObserve.postValue(interactor.getData(word, isOnline)) }
            }



        }

    fun handleError(error: Throwable) {
        liveDataToObserve.postValue(AppState.Error(error))
    }
}