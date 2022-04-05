package ru.gb.dictionary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class MainCoroutineRule(val dispatcher: TestDispatcher = UnconfinedTestDispatcher()) : TestWatcher(){
        override fun starting(description: Description?) {
            super.starting(description)
            Dispatchers.setMain(dispatcher)
        }

        override fun finished(description: Description?) {
            super.finished(description)
            Dispatchers.resetMain()
        }
    }