package ru.gb.dictionary.view.main

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.gb.dictionary.R


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    lateinit var scenario: ActivityScenario<MainActivity>


    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }
    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }


    @Test
    fun activityTextView_NotNull() {
        scenario.onActivity {
            val fab =
                it.findViewById<View>(R.id.search_fab)
            assertNotNull(fab)
        }
    }

    @Test
    fun activityFab_isVisible() {
        scenario.onActivity {
            val fab =
                it.findViewById<View>(R.id.search_fab)
            assertEquals(View.VISIBLE, fab.visibility)
        }
    }




}