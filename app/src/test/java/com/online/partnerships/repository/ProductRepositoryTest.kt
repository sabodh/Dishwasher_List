package com.online.partnerships.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.online.partnerships.model.data.ProductDetailsData
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.model.repository.DefaultProductRepository
import com.online.partnerships.network.ServiceEndPoints
import com.online.partnerships.utils.Constants
import com.online.partnerships.utils.Status
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var serviceEndPoints: ServiceEndPoints

    private var PRODUCTID : String = "3218074"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test_GetProducts_success() = runTest {
        Mockito.`when`(
            serviceEndPoints
                .getProducts(Constants.DISH_WASHER, Constants.API_KEY)
        )
            .thenReturn(Response.success(ProductLists()))
        val repository = DefaultProductRepository(serviceEndPoints)
        val result = repository.getProductList(Constants.DISH_WASHER)
        Assert.assertEquals(Status.SUCCESS, result.status)
    }

    @Test
    fun test_GetProducts_error() = runTest {
        Mockito.`when`(serviceEndPoints.getProducts(Constants.DISH_WASHER, Constants.API_KEY))
            .thenReturn(Response.error(401, "Unauthorized".toResponseBody()))
        val repository = DefaultProductRepository(serviceEndPoints)
        val result = repository.getProductList(Constants.DISH_WASHER)
        Assert.assertEquals(Status.ERROR, result.status)
        Assert.assertEquals("Unauthorized", result.message)
    }

    @Test
    fun test_GetProduct_empty() = runTest {
        Mockito.`when`(
            serviceEndPoints
                .getProductDetails(PRODUCTID)
        )
            .thenReturn(Response.success(ProductDetailsData()))
        val repository = DefaultProductRepository(serviceEndPoints)
        val result = repository.getProductDetails(PRODUCTID)
        Assert.assertNotNull(result.data)
    }

    @Test
    fun test_GetProduct_success() = runTest {
        Mockito.`when`(serviceEndPoints
                .getProductDetails(PRODUCTID))
            .thenReturn(Response.success(ProductDetailsData()))
        val repository = DefaultProductRepository(serviceEndPoints)
        val result = repository.getProductDetails(PRODUCTID)
        Assert.assertEquals(Status.SUCCESS, result.status)
    }

    @Test
    fun test_GetProduct_error() = runTest {
        Mockito.`when`(serviceEndPoints.getProductDetails(PRODUCTID))
            .thenReturn(Response.error(401, "Unauthorized".toResponseBody()))
        val repository = DefaultProductRepository(serviceEndPoints)
        val result = repository.getProductDetails(PRODUCTID)
        Assert.assertEquals(Status.ERROR, result.status)
        Assert.assertEquals("Unauthorized", result.message)
    }


}