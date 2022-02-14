package ru.gb.repository.repository




import ru.gb.model.dto.SearchResultDto
import ru.gb.repository.room.DataSourceLocal

class RepositoryImplLocal(private val dataSource: DataSourceLocal<List<SearchResultDto>>) :
    RepositoryLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: ru.gb.model.AppState) {
        dataSource.saveToDB(appState)
    }
}
