package ru.gb.dictionary.presenter




import ru.gb.dictionary.Utils.mapSearchResultToResult
import ru.gb.model.AppState
import ru.gb.model.dto.SearchResultDto
import ru.gb.repository.repository.Repository
import ru.gb.repository.repository.RepositoryLocal


class MainInteract (
    private val remoteRepository: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>

    ): ru.gb.core.Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState

        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(remoteRepository.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }

}