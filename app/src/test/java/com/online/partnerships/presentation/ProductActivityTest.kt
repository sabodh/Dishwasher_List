package com.online.partnerships.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.model.data.Products
import com.online.partnerships.model.getOrAwaitValue
import com.online.partnerships.model.repository.DefaultProductRepository
import com.online.partnerships.presentation.viewmodel.ProductViewModel
import com.online.partnerships.utils.Constants
import com.online.partnerships.utils.Responses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class ProductActivityTest {
    private lateinit var productActivity: ProductActivity

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        productActivity = ProductActivity()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_Products_receiving_properly() = runTest {
//        val activity = Robolectric.buildActivity(ProductActivity::class.java)
//            .create()
//            .resume()
//            .visible()
//            .get()
        val productRepository = Mockito.mock(DefaultProductRepository::class.java)
        val viewModel = ProductViewModel(productRepository)


        val products = arrayListOf(
            Products(
                title = "Bosch Serie 2 SMS24AW01G Freestanding Dishwasher, White",
                productId = "3218074"
            ),
            Products(
                title = "Bosch Serie 2 SMV40C30GB Fully Integrated Dishwasher",
                productId = "1955287"
            ),
            Products(
                title = "Bosch Serie 2 SPV2HKX39G Fully Integrated Slimline Dishwasher",
                productId = "5095447"
            ),
            Products(
                title = "Bosch Serie 4 SMV46NX00G Fully Integrated Dishwasher",
                productId = "5029064"
            )
        )

        Mockito.`when`(productRepository.getProductList(Constants.DISH_WASHER))
            .thenReturn(Responses.success(ProductLists(products = products)))
        viewModel.getProducts(Constants.DISH_WASHER)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.products.getOrAwaitValue()
        Assert.assertNotNull(result)


//        val observer = Observer<Responses<ProductLists>> { response ->
//            assertNotNull(response)
//            assertNotNull(response.data)
//            assertNotNull(response.data?.products)
//        }
//
//        viewModel.products.observeForever(observer)
//        viewModel.products.removeObserver(observer)

    }

    @Test
    fun test_fragment(){
//        val productFragment = launchFragmentInContainer<ProductFragment>()
//        productFragment.onFragment { fragment ->
//            runBlocking {
//                assertNotNull(fragment.arguments)
//                assertNotNull(fragment.arguments?.getString(Constants.BUNDLE_PRODUCT_ID))
//            }
//        }
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}