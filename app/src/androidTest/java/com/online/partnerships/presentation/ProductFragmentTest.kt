package com.online.partnerships.presentation

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_ID
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_PRICE
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert
@RunWith(AndroidJUnit4::class)
class ProductFragmentTest {


    @Test
    fun test_runFragment() {
        val args = Bundle().apply {
            putString(BUNDLE_PRODUCT_ID,  "3218074")
            putString(BUNDLE_PRODUCT_PRICE, "£220")
        }
        val scenario = launchFragmentInContainer<ProductFragment>(
            initialState = Lifecycle.State.CREATED
        )
        scenario.onFragment { fragment ->

            fragment.arguments = args
            val textView = fragment.binding?.txtAmount
            Assert.assertNotNull(onView(withId(textView!!.id)).check(matches(withText("£220"))))
        }
        scenario.close()

    }
}