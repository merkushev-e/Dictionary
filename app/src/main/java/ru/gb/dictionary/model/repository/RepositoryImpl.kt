package ru.gb.dictionary.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.gb.dictionary.model.DataSource
import ru.gb.dictionary.model.data.DataModel


class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
       return dataSource.getData(word)
    }

}