package ru.gb.dictionary.presenter



import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.gb.dictionary.AppState
import ru.gb.dictionary.model.room.DataSourceLocal
import ru.gb.dictionary.model.retrofit.DataSourceRemote
import ru.gb.dictionary.model.repository.RepositoryImpl
import ru.gb.dictionary.view.View

class MainPresenterImpl<T: AppState, V: View> (
    private val interactor: MainInteract = MainInteract(
        RepositoryImpl(DataSourceRemote()),
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : Presenter<T,V>{

    private var currentView: V? = null
    // Как только появилась View, сохраняем ссылку на неё для дальнейшей работы

    override fun attachView(view: V) {
        if (view != currentView){
            currentView = view
        }
    }



    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {

                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView){
            currentView = null
        }

    }

}

