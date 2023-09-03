package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class ProductMedia (
  @SerializedName("images"    ) var images    : ProductImages?    = ProductImages()

)