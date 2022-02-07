package ru.gb.dictionary.model.repository


import ru.gb.dictionary.model.DataSource
import ru.gb.dictionary.model.data.DataModel


class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
       return dataSource.getData(word)
    }

}