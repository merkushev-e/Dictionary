package ru.gb.dictionary.UIAutomator


import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.gb.dictionary.*
import ru.gb.dictionary.TEST_HI
import ru.gb.dictionary.context
import ru.gb.dictionary.uiDevice

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {

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
        val searchFab = uiDevice.findObject(By.res(packageName, "search_fab"))

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
        espressoClick(R.id.search_fab)
        uiDevice.wait(
            Until.findObject(By.res(packageName, "search_edit_text")),
            TIMEOUT
        )
        val editText1 = uiDevice.findObject(By.res(packageName, "search_edit_text"))

        editText1.text = TEST_HI


        espressoClick(R.id.search_button_textview)

        val recyclerViewItem1 =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "header_textview_recycler_item")),
                TIMEOUT
            )

        Assert.assertNotNull(recyclerViewItem1)
    }


    @Test
    fun test_OpenDetails() {
        espressoClick(R.id.search_fab)
        uiDevice.wait(
            Until.findObject(By.res(packageName, "search_edit_text")),
            TIMEOUT
        )
        val editText = uiDevice.findObject(By.res(packageName, "search_edit_text"))

        editText.text = TEST_HI


        espressoClick(R.id.search_button_textview)

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

        Assert.assertEquals(headerDetail.text.toString(), TEST_HI)


    }


}
