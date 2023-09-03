package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class ProductAdditionalServices(
    @SerializedName("includedServices") var includedServices: ArrayList<String> = arrayListOf()

)