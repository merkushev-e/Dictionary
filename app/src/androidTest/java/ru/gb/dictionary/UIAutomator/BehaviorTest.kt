package ru.gb.dictionary.UIAutomator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.gb.dictionary.R

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName


    @Before
    fun setup() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)

        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }


    @Test
    fun test_MainActivityIsStarted() {
        val res: BySelector = By.res(packageName, "search_fab")
        val searchFab = uiDevice.findObject(res)

        Assert.assertNotNull(searchFab)
    }

    @Test
    fun test_RecyclerViewIsNull() {

        val recyclerViewItem =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "header_textview_recycler_item")),
                TIMEOUT
            )

        Assert.assertNull(recyclerViewItem)
    }
    @Test
    fun test_RecyclerViewNotNullAfterSearching() {

        val fab = uiDevice.findObject(By.res(packageName, "search_fab"))
        fab.click()
        uiDevice.wait(
            Until.findObject(By.res(packageName, "search_edit_text")),
            TIMEOUT
        )
        val editText = uiDevice.findObject(By.res(packageName, "search_edit_text"))

        editText.text = "Hi"


        Espresso.onView(ViewMatchers.withId(R.id.search_button_textview))
            .perform(click())

        val recyclerViewItem =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "header_textview_recycler_item")),
                TIMEOUT
            )

        Assert.assertNotNull(recyclerViewItem)
    }




    @Test
    fun test_OpenDetails(){
        val fab = uiDevice.findObject(By.res(packageName, "search_fab"))
        fab.click()
        uiDevice.wait(
            Until.findObject(By.res(packageName, "search_edit_text")),
            TIMEOUT
        )
        val editText = uiDevice.findObject(By.res(packageName, "search_edit_text"))

        editText.text = "Hi"


        Espresso.onView(ViewMatchers.withId(R.id.search_button_textview))
            .perform(click())

        val recyclerViewItem =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "header_textview_recycler_item")),
                TIMEOUT
            )

        recyclerViewItem.click()

        val headerDetail = uiDevice.wait(
            Until.findObject(By.res(packageName, "description_header")),
            TIMEOUT
        )

        Assert.assertEquals(headerDetail.text.toString(), "hi")


    }

    companion object {
        private const val TIMEOUT = 6000L
    }


}
