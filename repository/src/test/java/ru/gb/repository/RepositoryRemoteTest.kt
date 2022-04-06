package ru.gb.repository

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.gb.model.dto.SearchResultDto
import ru.gb.repository.repository.RepositoryImpl

class RepositoryRemoteTest {
    private lateinit var repositoryRemote: RepositoryImpl

    @Mock
    private lateinit var dataSource: DataSource<List<SearchResultDto>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        repositoryRemote = RepositoryImpl(dataSource)
    }


    @Test
    fun getData_Test_remoteRepos() {
        runBlocking {
            val searchQuery = "some query"
            val searchResult = listOf(Mockito.mock(SearchResultDto::class.java))


            Mockito.`when`(dataSource.getData(searchQuery)).thenReturn(searchResult)
            repositoryRemote.getData(searchQuery)

            Mockito.verify(dataSource, Mockito.times(1)).getData(searchQuery)
        }
    }





}