package com.example.bintest.data.database

import com.google.gson.annotations.SerializedName

data class BankDbModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("city")
    val city: String? = null
)