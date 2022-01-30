package ru.gb.dictionary.presenter




import ru.gb.dictionary.AppState
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository


class MainInteract (
    private val remoteRepository: Repository<List<DataModel>>,

    ): Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
            return AppState.Success(remoteRepository.getData(word))
    }


}