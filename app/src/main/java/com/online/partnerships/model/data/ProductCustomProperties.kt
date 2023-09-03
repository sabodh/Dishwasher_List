package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class ProductCustomProperties(

    @SerializedName("warrantyCustomGeneric") var warrantyCustomGeneric: String? = null,
    @SerializedName("warrantyCustomTooltip") var warrantyCustomTooltip: String? = null,
    @SerializedName("warrantyCustomDescription") var warrantyCustomDescription: String? = null,
    @SerializedName("warrantyCustomConfirm") var warrantyCustomConfirm: String? = null

)