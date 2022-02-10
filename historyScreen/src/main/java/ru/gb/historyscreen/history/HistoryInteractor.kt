package values



import ru.gb.core.Interactor
import ru.gb.model.AppState
import ru.gb.model.DataModel
import ru.gb.repository.repository.Repository
import ru.gb.repository.repository.RepositoryLocal


class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): ru.gb.model.AppState {
        return ru.gb.model.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
