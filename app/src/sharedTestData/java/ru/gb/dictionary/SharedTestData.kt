package ru.gb.dictionary

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice


internal const val TEST_HI = "hi"
internal const val TIMEOUT = 6000L
internal val context = ApplicationProvider.getApplicationContext<Context>()
internal val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

fun espressoClick(id: Int){
    Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
}