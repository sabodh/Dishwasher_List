package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class Details(

    @SerializedName("facetId") var facetId: String? = null,
    @SerializedName("trackingId") var trackingId: String? = null,
    @SerializedName("label") var label: String? = null,
    @SerializedName("qty") var qty: String? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("colorSwatchUrl") var colorSwatchUrl: String? = null,
    @SerializedName("isSelected") var isSelected: String? = null,
    @SerializedName("productInformation") var productInformation: String? = null

)