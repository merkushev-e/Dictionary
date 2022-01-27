package ru.gb.dictionary.presenter



import io.reactivex.rxjava3.core.Observable
import ru.gb.dictionary.AppState
import ru.gb.dictionary.DI.NAME_REMOTE
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository
import javax.inject.Inject
import javax.inject.Named

class MainInteract @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,

    ): Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
            return remoteRepository.getData(word).map { AppState.Success(it) }
    }


}