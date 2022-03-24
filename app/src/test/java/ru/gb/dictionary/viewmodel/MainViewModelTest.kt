package ru.gb.dictionary.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.whenever
import getOrAwaitValue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.KoinContextHandler
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

import ru.gb.dictionary.presenter.MainInteract
import ru.gb.model.AppState
import ru.gb.model.data.DataModel
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var mainViewModel: MainViewModel

    val dispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var interactor: MainInteract



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(interactor, UnconfinedTestDispatcher())
        Dispatchers.setMain(dispatcher)
    }


    @Test
    fun getDataTest_AppStateSuccess() {
        val result = AppState.Success(data = listOf(mock(DataModel::class.java)))
        runTest {
            whenever(interactor.getData("query", true)).thenReturn(result)
            mainViewModel.getData("query", true)
            assertThat(mainViewModel.liveData.getOrAwaitValue(), `is`(result))
        }

    }


    @Test
    fun getDataTest_AppStateError() {
        val exception = RuntimeException("Exception")
        val result = AppState.Error(exception)

        runTest {
            whenever(interactor.getData("query", true)).thenThrow(exception)
            mainViewModel.getData("query", true)
        }
        assertThat(mainViewModel.liveData.getOrAwaitValue(), `is`(result))

    }

    @After
    fun tearAfter() {
        Dispatchers.resetMain()
        KoinContextHandler.stop()
    }

}