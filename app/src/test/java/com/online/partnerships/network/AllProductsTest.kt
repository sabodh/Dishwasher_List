package com.online.partnerships.network

import com.online.partnerships.Helper
import com.online.partnerships.utils.Constants
import com.online.partnerships.utils.Constants.API_KEY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class AllProductsTest {

    lateinit var mockServer: MockWebServer
    lateinit var serviceEndPoints: ServiceEndPoints

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        serviceEndPoints = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ServiceEndPoints::class.java)

    }

    /**
     * Test for success result
     */
    @Test
    fun test_GetProducts_success() = runTest{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/data.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockServer.enqueue(mockResponse)
        val response = serviceEndPoints.getProducts(Constants.DISH_WASHER, API_KEY)
        mockServer.takeRequest()
        Assert.assertEquals(true, response.isSuccessful)

    }
    /**
     * Test for failure 404 result
     */
    @Test
    fun test_GetProducts_error() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockServer.enqueue(mockResponse)
        val response = serviceEndPoints.getProducts(Constants.DISH_WASHER, API_KEY)
        mockServer.takeRequest()
        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())

    }
    /**
     * Test for empty result
     */
    @Test
    fun test_GetProducts_empty() = runTest{
        val mockResponse = MockResponse()
        val content = "{}"
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockServer.enqueue(mockResponse)
        val response = serviceEndPoints.getProducts(Constants.DISH_WASHER, API_KEY)
        mockServer.takeRequest()
        Assert.assertEquals(true, response.isSuccessful)

    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }
}