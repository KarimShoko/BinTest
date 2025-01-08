package com.example.bintest.data.network.model

import com.google.gson.annotations.SerializedName

data class BankDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("city")
    val city: String? = null
)