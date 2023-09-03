package com.online.partnerships.network
import com.online.partnerships.Helper
import com.online.partnerships.model.data.ProductDetailsData
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
class ProductDetailsTest {

    lateinit var mockServer: MockWebServer
    lateinit var serviceEndPoints: ServiceEndPoints
    private var PRODUCTID : String = "3218074"

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
    fun test_GetProduct_success() = runTest{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/product.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockServer.enqueue(mockResponse)
        val response = serviceEndPoints.getProductDetails(PRODUCTID)
        mockServer.takeRequest()
        Assert.assertEquals(true, response.isSuccessful)
        Assert.assertNotNull(PRODUCTID, response.body()?.productId)
        Assert.assertNotNull(response.body()?.title)

    }
    /**
     * Test for success result
     */

    @Test
    fun test_GetProduct_success_with_value() = runTest{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/product.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockServer.enqueue(mockResponse)
        val response = serviceEndPoints.getProductDetails(PRODUCTID)
        mockServer.takeRequest()
        val expected = ProductDetailsData(productId = PRODUCTID,
        title = "Bosch Serie 2 SMS24AW01G Freestanding Dishwasher, White")
        Assert.assertEquals(true, response.isSuccessful)
        Assert.assertEquals(expected.title, response.body()?.title)

    }
    /**
     * Test for failure 404 result
     */
    @Test
    fun test_GetProduct_error() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockServer.enqueue(mockResponse)
        val response = serviceEndPoints.getProductDetails(PRODUCTID)
        mockServer.takeRequest()
        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())

    }
    /**
     * Test for empty result
     */
    @Test
    fun test_GetProduct_empty() = runTest{
        val mockResponse = MockResponse()
        val content = "{}"
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockServer.enqueue(mockResponse)
        val response = serviceEndPoints.getProductDetails(PRODUCTID)
        mockServer.takeRequest()
        Assert.assertEquals(true, response.isSuccessful)
        Assert.assertNull(null, response.body()?.productId)

    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }
}