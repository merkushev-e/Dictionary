package ru.gb.dictionary.view.main


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.gb.dictionary.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTestAuto {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTestAuto() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.search_fab),
                childAtPosition(
                    allOf(
                        withId(R.id.success_linear_layout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.search_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.search_input_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("hello"), closeSoftKeyboard())

        val appCompatTextView = onView(
            allOf(
                withId(R.id.search_button_textview), withText("Search"),
                childAtPosition(
                    allOf(
                        withId(R.id.bottom_sheet_dialog_layout),
                        childAtPosition(
                            withId(R.id.design_bottom_sheet),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.main_activity_recyclerview),
                childAtPosition(
                    withId(R.id.success_linear_layout),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textView = onView(
            allOf(
                withId(R.id.description_header), withText("hello"),
                withParent(withParent(withId(R.id.description_screen_swipe_refresh_layout))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("hello")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
