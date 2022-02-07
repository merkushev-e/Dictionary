package ru.gb.dictionary.presenter

import ru.gb.dictionary.AppState
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository
import ru.gb.dictionary.model.repository.RepositoryLocal


class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
