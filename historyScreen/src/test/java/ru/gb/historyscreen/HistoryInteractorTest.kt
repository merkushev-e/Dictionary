package ru.gb.historyscreen

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.gb.historyscreen.history.HistoryInteractor
import ru.gb.model.dto.SearchResultDto
import ru.gb.repository.repository.Repository
import ru.gb.repository.repository.RepositoryLocal

class HistoryInteractorTest {

    private lateinit var interactor: HistoryInteractor

    @Mock
    private lateinit var remoteRepository: Repository<List<SearchResultDto>>


    @Mock
    private lateinit var repositoryLocal: RepositoryLocal<List<SearchResultDto>>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        interactor = HistoryInteractor(remoteRepository, repositoryLocal)
    }


    @Test
    fun getData_Test_remoteRepos() {
        runBlocking {
            val searchQuery = "some query"
            val searchResult = listOf(Mockito.mock(SearchResultDto::class.java))

            Mockito.`when`(remoteRepository.getData(searchQuery)).thenReturn(searchResult)

            interactor.getData(searchQuery, true)


            Mockito.verify(remoteRepository, Mockito.times(1)).getData(searchQuery)
        }
    }

    @Test
    fun getData_Test_localeRepos() {
        runBlocking {
            val searchQuery = "some query"
            val searchResult = listOf(Mockito.mock(SearchResultDto::class.java))

            Mockito.`when`(repositoryLocal.getData(searchQuery)).thenReturn(searchResult)

            interactor.getData("some query", false)

            Mockito.verify(repositoryLocal, Mockito.times(1)).getData(searchQuery)
        }
    }



}
