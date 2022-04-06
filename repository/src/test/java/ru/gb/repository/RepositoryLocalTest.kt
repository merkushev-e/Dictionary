package ru.gb.repository

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.gb.model.AppState
import ru.gb.model.dto.SearchResultDto
import ru.gb.repository.repository.RepositoryImpl
import ru.gb.repository.repository.RepositoryImplLocal
import ru.gb.repository.room.DataSourceLocal

class RepositoryLocalTest {

    private lateinit var repositoryRemote: RepositoryImplLocal

    @Mock
    private lateinit var dataSource: DataSourceLocal<List<SearchResultDto>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        repositoryRemote = RepositoryImplLocal(dataSource)
    }

    @Test
    fun getData_Test() {
        runBlocking {
            val searchQuery = "some query"
            val searchResult = listOf(Mockito.mock(SearchResultDto::class.java))


            Mockito.`when`(dataSource.getData(searchQuery)).thenReturn(searchResult)
            repositoryRemote.getData(searchQuery)

            Mockito.verify(dataSource, Mockito.times(1)).getData(searchQuery)
        }
    }

    @Test
    fun saveToDB_Test() {
        runBlocking {
            val appState = Mockito.mock(AppState::class.java)

            repositoryRemote.saveToDB(appState)

            Mockito.verify(dataSource, Mockito.times(1)).saveToDB(appState)
        }
    }



}