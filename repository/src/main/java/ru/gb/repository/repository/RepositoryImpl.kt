package ru.gb.repository.repository



import ru.gb.model.data.DataModel
import ru.gb.model.dto.SearchResultDto
import ru.gb.repository.DataSource


class RepositoryImpl(private val dataSource: DataSource<List<SearchResultDto>>) :
    Repository<List<SearchResultDto>> {
    override suspend fun getData(word: String): List<SearchResultDto> {
       return dataSource.getData(word)
    }

}