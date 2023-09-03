package com.online.partnerships.model.data

import com.google.gson.annotations.SerializedName


data class DynamicAttributes(

    @SerializedName("dimensions") var dimensions: String? = null,
    @SerializedName("delicatewash") var delicatewash: String? = null,
    @SerializedName("childlock") var childlock: String? = null,
    @SerializedName("timerdelay") var timerdelay: String? = null,
    @SerializedName("dryingperformance") var dryingperformance: String? = null,
    @SerializedName("dryingsystem") var dryingsystem: String? = null,
   // provided Any because of it can retrieve as array and a single text
    @SerializedName("adjustable") var adjustable: Any? = null


)