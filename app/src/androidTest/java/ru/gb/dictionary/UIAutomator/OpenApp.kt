package ru.gb.dictionary.UIAutomator

import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import ru.gb.dictionary.uiDevice

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class OpenApp {


    @Test
    fun test_OpenSettings() {
        uiDevice.pressHome()
        uiDevice.swipe(500, 1500, 500, 0, 5)
        val scrollable: UiSelector = UiSelector().scrollable(false)
        val appViews: UiScrollable = UiScrollable(scrollable)

        val className: UiSelector = UiSelector().className(TextView::class.java.name)
        val dictionaryApp = appViews
            .getChildByText(
                className,
                "Dictionary"
            )

        dictionaryApp.clickAndWaitForNewWindow()

        val settingsValidation =
            uiDevice.findObject(UiSelector().packageName("ru.gb.dictionary"))
        Assert.assertTrue(settingsValidation.exists())

    }
}
