package com.example.bintest.domain.entity

data class CardInfo(
    val binNumber: Int,
    val scheme: String,
    val type: String,
    val brand: String,
    val country: Country,
    val bank: Bank,
)