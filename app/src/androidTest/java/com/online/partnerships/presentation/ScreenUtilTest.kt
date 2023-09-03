package com.online.partnerships.presentation

import android.content.Context
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import com.online.partnerships.utils.ScreenUtils.getGridCount
import org.junit.Assert.assertEquals
import org.junit.Test

class ScreenUtilTest {
    /**
     * Test class used to get the screen orientation details to show the product
     */

    @Test
    fun test_getGridCount_returnsCorrectTabPortrait() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_XLARGE
            orientation = Configuration.ORIENTATION_PORTRAIT
        }
        val newContext = context.createConfigurationContext(configuration)
        val gridCount = getGridCount(newContext)
        assertEquals(3, gridCount)
    }

    @Test
    fun test_getGridCount_returnsTabLandscape() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_XLARGE
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        val newContext = context.createConfigurationContext(configuration)
        val gridCount = getGridCount(newContext)
        assertEquals(4, gridCount)
    }

    @Test
    fun test_getGridCount_returnsPhoneLandscape() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_NORMAL
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        val newContext = context.createConfigurationContext(configuration)
        val gridCount = getGridCount(newContext)
        assertEquals(3, gridCount)
    }

    @Test
    fun test_getGridCount_returnsPhonePortrait() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_NORMAL
            orientation = Configuration.ORIENTATION_PORTRAIT
        }
        val newContext = context.createConfigurationContext(configuration)
        val gridCount = getGridCount(newContext)
        assertEquals(2, gridCount)
    }

    @Test
    fun test_getGridCount_returnsSmallTabPortrait() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_LARGE
            orientation = Configuration.ORIENTATION_PORTRAIT
        }
        val newContext = context.createConfigurationContext(configuration)
        val gridCount = getGridCount(newContext)
        assertEquals(3, gridCount)
    }

    @Test
    fun test_getGridCount_returnsSmallTabLandscape() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_LARGE
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        val newContext = context.createConfigurationContext(configuration)
        val gridCount = getGridCount(newContext)
        assertEquals(4, gridCount)
    }

    @Test
    fun test_getGridCount_returnsSmallTabLandscape_failure() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_XLARGE
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        val newContext = context.createConfigurationContext(configuration)
        val gridCount = getGridCount(newContext)
        assertEquals(3, gridCount)
    }
}
