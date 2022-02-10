package ru.gb.repository.repository



import ru.gb.model.DataModel
import ru.gb.repository.DataSource


class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
       return dataSource.getData(word)
    }

}