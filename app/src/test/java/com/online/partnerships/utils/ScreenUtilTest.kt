package com.online.partnerships.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.online.partnerships.utils.ScreenUtils.getGridCount
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

/**
 * Test class for checking screen resolution
 */
class ScreenUtilTest {

    @Test
    fun test_getGridCount_returnsCorrectTabPortrait() {
        val context = Mockito.mock(Context::class.java)
        val resources = Mockito.mock(Resources::class.java)
        Mockito.`when`(context.resources).thenReturn(resources)
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_XLARGE
            orientation = Configuration.ORIENTATION_PORTRAIT
        }
        Mockito.`when`(resources.configuration).thenReturn(configuration)
        val gridCount = getGridCount(context)
        assertEquals(3, gridCount)
    }
    @Test
    fun test_getGridCount_returnsTabLandscape() {
        val context = Mockito.mock(Context::class.java)
        val resources = Mockito.mock(Resources::class.java)
        Mockito.`when`(context.resources).thenReturn(resources)
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_XLARGE
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        Mockito.`when`(resources.configuration).thenReturn(configuration)
        val gridCount = getGridCount(context)
        assertEquals(4, gridCount)
    }
    @Test
    fun test_getGridCount_returnsPhoneLandscape() {
        val context = Mockito.mock(Context::class.java)
        val resources = Mockito.mock(Resources::class.java)
        Mockito.`when`(context.resources).thenReturn(resources)
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_NORMAL
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        Mockito.`when`(resources.configuration).thenReturn(configuration)
        val gridCount = getGridCount(context)
        assertEquals(3, gridCount)
    }
    @Test
    fun test_getGridCount_returnsPhonePortrait() {
        val context = Mockito.mock(Context::class.java)
        val resources = Mockito.mock(Resources::class.java)
        Mockito.`when`(context.resources).thenReturn(resources)
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_NORMAL
            orientation = Configuration.ORIENTATION_PORTRAIT
        }
        Mockito.`when`(resources.configuration).thenReturn(configuration)
        val gridCount = getGridCount(context)
        assertEquals(2, gridCount)
    }
    @Test
    fun test_getGridCount_returnsSmallTabPortrait() {
        val context = Mockito.mock(Context::class.java)
        val resources = Mockito.mock(Resources::class.java)
        Mockito.`when`(context.resources).thenReturn(resources)
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_LARGE
            orientation = Configuration.ORIENTATION_PORTRAIT
        }
        Mockito.`when`(resources.configuration).thenReturn(configuration)
        val gridCount = getGridCount(context)
        assertEquals(3, gridCount)
    }
    @Test
    fun test_getGridCount_returnsSmallTabLandscape() {
        val context = Mockito.mock(Context::class.java)
        val resources = Mockito.mock(Resources::class.java)
        Mockito.`when`(context.resources).thenReturn(resources)
        val configuration = Configuration().apply {
            screenLayout = Configuration.SCREENLAYOUT_SIZE_LARGE
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        Mockito.`when`(resources.configuration).thenReturn(configuration)
        val gridCount = getGridCount(context)
        assertEquals(4, gridCount)
    }
}
