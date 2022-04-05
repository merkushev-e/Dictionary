package ru.gb.dictionary

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.*
import ru.gb.dictionary.DI.application
import ru.gb.dictionary.DI.historyScreen
import ru.gb.dictionary.DI.mainScreen
import ru.gb.dictionary.view.main.MainActivity


@RunWith(AndroidJUnit4::class)


class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>



    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)

    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityFab_isVisible() {
        onView(withId(R.id.search_fab)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun searchDialog_isDisplayed() {
        espressoClick(R.id.search_fab)
        onView(withId(R.id.bottom_sheet_dialog_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun searchDialog_isCompletelyDisplayed() {
        espressoClick(R.id.search_fab)
        onView(withId(R.id.bottom_sheet_dialog_layout)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun searchDialog_editText_isDisplayed() {
        espressoClick(R.id.search_fab)
        onView(withId(R.id.search_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun activityRV_isShowingAfterSearch() {
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(listOf(application, mainScreen, historyScreen))
        }
        onView(withId(R.id.search_fab)).perform(click())
        espressoClick(R.id.search_edit_text)
        onView(withId(R.id.search_edit_text)).perform(
            replaceText(TEST_HI),
            closeSoftKeyboard()
        )

        espressoClick(R.id.search_button_textview)
        onView(isRoot()).perform(delay())
        onView(withId(R.id.main_activity_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.search_fab)).check(matches(isDisplayed()))
        stopKoin()
    }

    private fun delay(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(3000)
            }
        }
    }


    @After
    fun close() {
        stopKoin()
        scenario.close()
    }
}
