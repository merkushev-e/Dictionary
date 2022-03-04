package ru.gb.dictionary


import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.gb.dictionary.Utils.mapSearchResultToResult
import ru.gb.dictionary.presenter.MainInteract
import ru.gb.model.AppState
import ru.gb.model.data.DataModel
import ru.gb.model.data.Meanings
import ru.gb.model.data.Translation
import ru.gb.model.dto.MeaningsDto
import ru.gb.model.dto.SearchResultDto
import ru.gb.model.dto.TranslationDto
import ru.gb.repository.repository.Repository
import ru.gb.repository.repository.RepositoryLocal

class MainInteractorTest {

    private lateinit var interactor: MainInteract

    @Mock
    private lateinit var remoteRepository: Repository<List<SearchResultDto>>


    @Mock
    private lateinit var repositoryLocal: RepositoryLocal<List<SearchResultDto>>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        interactor = MainInteract(remoteRepository, repositoryLocal)
    }

    @Test
     fun getData_Test_remoteRepos() {
        runBlocking {
            val searchQuery = "some query"
            val searchResult = listOf(mock(SearchResultDto::class.java))


            `when`(remoteRepository.getData(searchQuery)).thenReturn(searchResult)
            interactor.getData("some query", true)

            verify(remoteRepository, times(1)).getData(searchQuery)
        }
    }

    @Test
    fun getData_Test_localeRepos() {
        runBlocking {
            val searchQuery = "some query"
            val searchResult = listOf(mock(SearchResultDto::class.java))
            val appState = mock(AppState::class.java)


            `when`(repositoryLocal.getData(searchQuery)).thenReturn(searchResult)

            interactor.getData("some query", false)
            
            verify(repositoryLocal, times(1)).getData(searchQuery)
        }
    }



    @Test
    fun getData_Test_localeReposDb2() {
        runBlocking {
            val searchQuery = "some query"
            val searchResult = listOf(mock(SearchResultDto::class.java))
            val data = AppState.Success(data = listOf(DataModel()))

            `when`(remoteRepository.getData(searchQuery)).thenReturn(searchResult)
            interactor.getData(searchQuery, true)

            verify(repositoryLocal, times(1)).saveToDB(data)
        }
    }




}