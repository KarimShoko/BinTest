package com.example.bintest.data.database

import com.google.gson.annotations.SerializedName

data class NumberDbModel(
    @SerializedName("length")
    val length: Int? = 0,
    @SerializedName("luhn")
    val luhn: String? = null
)