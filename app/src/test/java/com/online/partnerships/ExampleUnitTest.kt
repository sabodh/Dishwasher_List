package com.online.partnerships

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Before
    fun setUp() {

        MockitoAnnotations.openMocks(this)
//        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}