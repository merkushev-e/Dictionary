package ru.gb.historyscreen.history



import ru.gb.core.Interactor
import ru.gb.dictionary.Utils.mapSearchResultToResult
import ru.gb.model.AppState
import ru.gb.model.data.DataModel
import ru.gb.model.dto.SearchResultDto
import ru.gb.repository.repository.Repository
import ru.gb.repository.repository.RepositoryLocal


class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}
