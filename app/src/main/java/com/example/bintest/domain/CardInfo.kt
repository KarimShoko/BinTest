package com.example.bintest.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "card_info_list")
data class CardInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val binNumber: Int?,
    @SerializedName("scheme")
    val scheme: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("prepaid")
    val prepaid: String? = null,
    @SerializedName("country")
    @Embedded(prefix = "country_")
    val Country: Country,
    @SerializedName("bank")
    @Embedded(prefix = "bank")
    val Bank: Bank,
    @SerializedName("number")
    @Embedded //чтобы не было ошибки(т.к непонятный тип данных)
    val Number: Number?
)