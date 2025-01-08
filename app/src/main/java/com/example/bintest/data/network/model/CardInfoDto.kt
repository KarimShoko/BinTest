package com.example.bintest.data.network.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class CardInfoDto(
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("country")
    @Embedded(prefix = "country_")
    val country: CountryDto,
    @SerializedName("bank")
    @Embedded(prefix = "bank")
    val bank: BankDto,
    @SerializedName("number")
    @Embedded
    val number: NumberDto
)