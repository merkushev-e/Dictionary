package ru.gb.dictionary.view.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.gb.dictionary.R
import ru.gb.dictionary.TEST_HI
import ru.gb.dictionary.espressoClick


class MainActivityRVTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)

    }

    @Test
    fun activitySearch() {
        onView(withId(R.id.search_fab)).perform(click())
        espressoClick(R.id.search_edit_text)
        onView(withId(R.id.search_edit_text)).perform(
            replaceText(TEST_HI),
            closeSoftKeyboard()
        )

        espressoClick(R.id.search_button_textview)
        onView(isRoot()).perform(delay())
        onView(withId(R.id.main_activity_recyclerview))
            .perform(
                RecyclerViewActions.scrollTo<MainAdapter.RecyclerItemViewHolder>(
                    hasDescendant(withText("historical"))
                )
            )

    }

    @Test
    fun activitySearch_performClickOnPosition() {
        onView(withId(R.id.search_fab)).perform(click())
        espressoClick(R.id.search_edit_text)
        onView(withId(R.id.search_edit_text)).perform(
            replaceText(TEST_HI),
            closeSoftKeyboard()
        )

        espressoClick(R.id.search_button_textview)
        onView(isRoot()).perform(delay())

        onView(withId(R.id.main_activity_recyclerview))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MainAdapter.RecyclerItemViewHolder>(
                    5,
                    click()
                )
            )

        onView(withId(R.id.description_header)).check(matches(withText("his")))

    }


    @Test
    fun activitySearch_performClickOnPositionAndCheckText() {
        onView(withId(R.id.search_fab)).perform(click())
        espressoClick(R.id.search_edit_text)
        onView(withId(R.id.search_edit_text)).perform(
            replaceText(TEST_HI),
            closeSoftKeyboard()
        )

        espressoClick(R.id.search_button_textview)
        onView(isRoot()).perform(delay())

        onView(withId(R.id.main_activity_recyclerview))
            .check(matches(atPosition(5, hasDescendant(withText("his")))))

    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
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
        scenario.close()
    }

}

