package ru.gb.dictionary.presenter



import io.reactivex.rxjava3.core.Observable
import ru.gb.dictionary.AppState
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository

class MainInteract(
    private val remoteRepository: Repository<List<DataModel>>,

): Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
            return remoteRepository.getData(word).map { AppState.Success(it) }
    }


}