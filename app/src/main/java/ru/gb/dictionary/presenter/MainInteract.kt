package ru.gb.dictionary.presenter




import ru.gb.dictionary.AppState
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository
import ru.gb.dictionary.model.repository.RepositoryLocal


class MainInteract (
    private val remoteRepository: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>

    ): Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState

        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }


}