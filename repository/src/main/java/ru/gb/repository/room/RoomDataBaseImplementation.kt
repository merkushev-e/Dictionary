package ru.gb.repository.room




import ru.gb.dictionary.Utils.convertDataModelSuccessToEntity
import ru.gb.dictionary.Utils.mapHistoryEntityToSearchResult
import ru.gb.model.data.DataModel
import ru.gb.model.dto.SearchResultDto

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: ru.gb.model.AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
