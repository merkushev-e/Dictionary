package ru.gb.dictionary.presenter




import ru.gb.model.AppState
import ru.gb.model.DataModel
import ru.gb.repository.repository.Repository
import ru.gb.repository.repository.RepositoryLocal


class MainInteract (
    private val remoteRepository: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>

    ): ru.gb.core.Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): ru.gb.model.AppState {
        val appState: ru.gb.model.AppState

        if (fromRemoteSource) {
            appState = ru.gb.model.AppState.Success(remoteRepository.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = ru.gb.model.AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }


}