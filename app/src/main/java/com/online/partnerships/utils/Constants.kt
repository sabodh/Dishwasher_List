package com.online.partnerships.utils

import com.online.partnerships.BuildConfig

object Constants {
    const val END_URL = "https://api.johnlewis.com/"
    const val API_KEY = BuildConfig.API_KEY
    const val DISH_WASHER = "dishwasher"

    const val HEADER_ACCEPT = "Accept"
    const val HEADER_ACCEPT_JSON = "application/json"
    const val HEADER_CACHE = "Cache-Control"
    const val HEADER_NO_CACHE = "no-cache"
    const val HEADER_USER_AGENT = "User-Agent"
    const val HEADER_HTTP = "http.agent"

    const val FRAGMENT_TAG = "ProductFragment"
    const val BUNDLE_PRODUCT_ID = "productId"
    const val BUNDLE_PRODUCT_PRICE = "productPrice"
    const val BUNDLE_PRODUCT_PRICE_DEFAULT = "£ 0.00"

}