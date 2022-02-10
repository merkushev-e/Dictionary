package ru.gb.repository.repository



import ru.gb.model.DataModel
import ru.gb.repository.room.DataSourceLocal

class RepositoryImplLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: ru.gb.model.AppState) {
        dataSource.saveToDB(appState)
    }
}
