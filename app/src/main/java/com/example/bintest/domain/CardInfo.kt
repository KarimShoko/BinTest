package com.example.bintest.domain

import androidx.room.Entity

@Entity(tableName = "card_info_list")
data class CardInfo(
    // val id: Int=0,
    val binNumber: Int?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: String? = "test",
    val country: Country,
    val bank: Bank,
    val number: Number?
)