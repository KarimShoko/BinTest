package com.example.bintest.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "card_info_list")
data class CardInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val binNumber: Int?,
    @SerializedName("scheme")
    val scheme: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("prepaid")
    val prepaid: String? = "test",
    @SerializedName("country")
    @Embedded(prefix = "country_")
    val country: CountryDbModel,
    @SerializedName("bank")
    @Embedded(prefix = "bank")
    val bank: BankDbModel,
    @SerializedName("number")
    @Embedded //чтобы не было ошибки(т.к непонятный тип данных)
    val number: NumberDbModel
)