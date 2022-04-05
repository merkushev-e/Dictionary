package ru.gb.dictionary.UIAutomator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import ru.gb.dictionary.context

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class InitialTest {
    private val packageName = context.packageName


    @Test
    fun test_DeviceNotNull() {

        val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
        Assert.assertNotNull(uiDevice)
    }


    @Test
    fun test_AppPackageNotNull() {
        Assert.assertNotNull(packageName)
    }


    @Test
    fun test_MainActivityIntentNotNull() {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        Assert.assertNotNull(intent)
    }


}