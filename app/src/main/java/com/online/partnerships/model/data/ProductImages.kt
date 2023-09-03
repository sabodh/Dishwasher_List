package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class ProductImages (

  @SerializedName("altText" ) var altText : String?           = null,
  @SerializedName("urls"    ) var urls    : ArrayList<String> = arrayListOf()

)