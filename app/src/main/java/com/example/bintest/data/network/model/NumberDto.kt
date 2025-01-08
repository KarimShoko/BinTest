package com.example.bintest.data.network.model

import com.google.gson.annotations.SerializedName

data class NumberDto (
    @SerializedName("length")
    val length:Int?=0,
    @SerializedName("luhn")
    val luhn:String?=null
)