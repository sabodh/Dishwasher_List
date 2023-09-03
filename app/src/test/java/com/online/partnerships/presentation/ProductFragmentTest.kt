package com.online.partnerships.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import com.online.partnerships.Helper
import com.online.partnerships.model.data.ProductDetailsData
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.model.data.Products
import com.online.partnerships.model.repository.DefaultProductRepository
import com.online.partnerships.presentation.viewmodel.ProductViewModel
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_ID
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_PRICE
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProductFragmentTest {

    private lateinit var mockItems: ArrayList<Products>
    private lateinit var productFragment: ProductFragment

    @Before
    fun setUp() {
        val content = Helper.readFileResource("/data.json")
        val jsonData = Helper.fromJson(content, ProductLists::class.java)
        mockItems = jsonData!!.products
        productFragment = ProductFragment()

    }

    @Test
    fun fragmentCreation() {
//        val activity = Robolectric.buildActivity(ProductActivity::class.java)
//            .create()
//            .resume()
//            .visible()
//            .get()
//        val uiView = activity.binding
//        Assert.assertNotNull(uiView)

        val fragment = launchFragmentInContainer<ProductFragment>()
        fragment.onFragment {
            assert(it.binding?.progressBar?.visibility == View.VISIBLE)
        }
    }

    @Test
    fun test_onCreateView() {
        val view = productFragment.onCreateView(
            LayoutInflater
                .from(ApplicationProvider.getApplicationContext()), null, null
        )
        Assert.assertNotNull(view)
    }

    @Test
    fun test_Fetch_Product_Details()  {
        val productRepository = mock(DefaultProductRepository::class.java)
        val viewModel = ProductViewModel(productRepository)

        // Set up the mock data
        val mockProductId = "3218074"
        val mockProductPrice = "£220"

        val mockProductDetails = ProductDetailsData(
            productId = "3218074",
            title = "Bosch Serie 2 SMS24AW01G Freestanding Dishwasher, White"
        )

        val bundle = Bundle().apply {
            putString(BUNDLE_PRODUCT_ID, mockProductId)
            putString(BUNDLE_PRODUCT_PRICE, mockProductPrice)
        }

        // Set up the fragment
        val productFragment = launchFragmentInContainer<ProductFragment>(bundle)
        productFragment.onFragment { fragment ->
            runBlocking {
                Assert.assertNotNull(fragment.arguments)
                Assert.assertNotNull(fragment.arguments?.getString(BUNDLE_PRODUCT_ID))
            }
        }
    }
    @After
    fun tearDown() {
    }
}