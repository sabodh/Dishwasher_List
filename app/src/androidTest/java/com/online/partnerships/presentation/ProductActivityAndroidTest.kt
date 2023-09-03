package com.online.partnerships.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.online.partnerships.R
import com.online.partnerships.adapter.ProductAdapter

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductActivityAndroidTest {

    @get:Rule
     val activityScenarioRule = ActivityScenarioRule(ProductActivity::class.java)

//    private val idlingResource = DataBindingIdlingResource(activityScenarioRule)


    @Before
    fun setUp() {
       // IdlingRegistry.getInstance().register(idlingResource)

    }

    @Test
    fun test_Loading(){
        onView(withId(R.id.progressBar))
            .check(matches(isDisplayed()))
//        onView(withId(R.id.recyclerView))
//            .check(matches(withEffectiveVisibility(Visibility.GONE)))
//        onView(withId(R.id.swipeRefreshLayout)).check(matches(not(isDisplayed())))

    }
    @Test
    fun test_RecyclerView(){
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
            .actionOnItemAtPosition<ProductAdapter.ProductViewHolder>(1, click()))
    }


    @After
    fun tearDown() {
//        IdlingRegistry.getInstance().unregister(idlingResource)

    }
}