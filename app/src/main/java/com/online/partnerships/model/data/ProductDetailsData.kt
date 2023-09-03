package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class ProductDetailsData(

    @SerializedName("productId") var productId: String? = null,
    @SerializedName("details") var details: Details? = Details(),
    @SerializedName("title") var title: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("displaySpecialOffer") var displaySpecialOffer: String? = null,
    @SerializedName("additionalServices") var additionalServices: ProductAdditionalServices? = ProductAdditionalServices(),
    @SerializedName("media") var media: ProductMedia? = ProductMedia(),
    @SerializedName("dynamicAttributes") var dynamicAttributes: DynamicAttributes? = DynamicAttributes()

)