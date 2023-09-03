package com.online.partnerships.model.repository

import com.online.partnerships.model.data.ProductDetailsData
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.network.ServiceEndPoints
import com.online.partnerships.utils.Constants.API_KEY
import com.online.partnerships.utils.Responses
import java.net.UnknownHostException

class DefaultProductRepository(private val serviceEndPoints: ServiceEndPoints) : ProductRepository {

    override suspend fun getProductList(product: String): Responses<ProductLists> {
        return try {
            val response = serviceEndPoints.getProducts(product, API_KEY)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Responses.success(it)
                } ?: Responses.error("Unknown Error", null)
            } else {
                Responses.error(response.errorBody()?.string() ?: "Unknown Error", null)
            }

        } catch (e: UnknownHostException) {
            Responses.error("Network Connection Lost!.", null)
        } catch (e: Exception) {
            Responses.error(e.message.toString(), null)
        }
    }

    override suspend fun getProductDetails(productId: String): Responses<ProductDetailsData> {
        return try {
            val response = serviceEndPoints.getProductDetails(productId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Responses.success(it)
                } ?: Responses.error("Unknown Error", null)
            } else {
                Responses.error(
                    response.errorBody()?.string() ?: "Unknown Error", null
                )
            }
        } catch (e: UnknownHostException) {
            Responses.error("Network Connection Lost!.", null)
        } catch (e: Exception) {
            Responses.error(e.message.toString(), null)
        }
    }
    private fun getName():String{
        return "Hello Kotlin"
    }
}