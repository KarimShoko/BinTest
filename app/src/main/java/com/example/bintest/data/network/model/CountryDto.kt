package com.example.bintest.data.network.model

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("latitude")
    val latitude: Int,
    @SerializedName("longitude")
    val longitude: Int
)