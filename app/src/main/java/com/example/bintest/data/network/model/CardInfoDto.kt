package com.example.bintest.data.network.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class CardInfoDto(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int=0,
 //   val binNumber: Int?,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("prepaid")
    val prepaid: String? ,
    @SerializedName("country")
    @Embedded(prefix = "country_")
    val country: CountryDto,
    @SerializedName("bank")
    @Embedded(prefix = "bank")
    val bank: BankDto,
    @SerializedName("number")
    @Embedded //чтобы не было ошибки(т.к непонятный тип данных)
    val number: NumberDto
)