package com.online.partnerships.network

import com.online.partnerships.model.data.ProductDetailsData
import com.online.partnerships.model.data.ProductLists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceEndPoints {

    @GET("search/api/rest/v2/catalog/products/search/keyword")
    suspend fun getProducts(@Query("q") product: String,
                             @Query("key") apiKey: String): Response<ProductLists>

    @GET("mobile-apps/api/v1/products/{productId}")
    suspend  fun getProductDetails(@Path("productId") productId: String): Response<ProductDetailsData>

}