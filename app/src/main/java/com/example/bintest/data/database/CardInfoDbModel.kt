package com.example.bintest.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_info_list")
data class CardInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val binNumber: Int,
    val scheme: String,
    val type: String,
    val brand: String,
    @Embedded(prefix = "country_")
    val country: CountryDbModel,
    @Embedded(prefix = "bank")
    val bank: BankDbModel,
    @Embedded
    val number: NumberDbModel
)