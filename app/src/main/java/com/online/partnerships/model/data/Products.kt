package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class Products(

    @SerializedName("productId") var productId: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("variantPriceRange") var variantPriceRange: VariantPriceRange? = VariantPriceRange()
)