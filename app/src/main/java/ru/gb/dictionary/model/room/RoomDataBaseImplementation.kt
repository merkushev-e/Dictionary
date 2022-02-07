package ru.gb.dictionary.model.room


import geekbrains.ru.translator.room.HistoryDao
import ru.gb.dictionary.AppState
import ru.gb.dictionary.Utils.convertDataModelSuccessToEntity
import ru.gb.dictionary.Utils.mapHistoryEntityToSearchResult
import ru.gb.dictionary.model.data.DataModel

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
