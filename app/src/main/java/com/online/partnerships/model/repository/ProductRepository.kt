package com.online.partnerships.model.repository

import com.online.partnerships.model.data.ProductDetailsData
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.utils.Responses

interface ProductRepository {

    suspend fun getProductList(product: String): Responses<ProductLists>

    suspend fun getProductDetails(productId: String): Responses<ProductDetailsData>
}