package com.online.partnerships.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.online.partnerships.model.data.ProductDetailsData
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.model.getOrAwaitValue
import com.online.partnerships.model.repository.DefaultProductRepository
import com.online.partnerships.utils.Constants
import com.online.partnerships.utils.Responses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: DefaultProductRepository

    private val errorResponse = Responses.error("Something went wrong", null)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_getProducts() = runTest {
        Mockito.`when`(repository.getProductList(Constants.DISH_WASHER))
            .thenReturn(Responses.success(ProductLists()))
        val viewModel = ProductViewModel(repository)
        viewModel.getProducts(Constants.DISH_WASHER)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.products.getOrAwaitValue()
        Assert.assertNotNull(result.data)

    }
    @Test
    fun test_getProducts_error() = runTest {
        Mockito.`when`(repository.getProductList(Constants.DISH_WASHER))
            .thenReturn(errorResponse)
        val viewModel = ProductViewModel(repository)
        viewModel.getProducts(Constants.DISH_WASHER)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.products.getOrAwaitValue()
        Assert.assertNotNull(result)
        Assert.assertEquals("Something went wrong", result.message)

    }

    @Test
    fun test_ProductDetails() = runTest{
        Mockito.`when`(repository.getProductDetails("3218074"))
            .thenReturn(Responses.success(ProductDetailsData()))
        val viewModel = ProductViewModel(repository)
        viewModel.getProductsDetails("3218074")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.productDetails.getOrAwaitValue()
        Assert.assertNotNull(result.data)

    }
    @Test
    fun test_ProductDetails_error() = runTest{
        Mockito.`when`(repository.getProductDetails("3218074"))
            .thenReturn(errorResponse)
        val viewModel = ProductViewModel(repository)
        viewModel.getProductsDetails("3218074")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.productDetails.getOrAwaitValue()
        Assert.assertNotNull(result)
        Assert.assertEquals("Something went wrong", result.message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}