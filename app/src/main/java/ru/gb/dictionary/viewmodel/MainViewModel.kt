package ru.gb.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.gb.dictionary.AppState
import ru.gb.dictionary.model.repository.RepositoryImpl
import ru.gb.dictionary.model.retrofit.DataSourceRemote
import ru.gb.dictionary.presenter.MainInteract
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteract,

) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState> = liveDataToObserve
    private lateinit var compositeDisposable: CompositeDisposable;




    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun getData(word: String, isOnline: Boolean) {
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveDataToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )

    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {

                liveDataToObserve.value = appState
            }

            override fun onError(e: Throwable) {
                liveDataToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }

}