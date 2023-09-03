package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class ProductLists(
    @SerializedName("products") var products: ArrayList<Products> = arrayListOf()

)