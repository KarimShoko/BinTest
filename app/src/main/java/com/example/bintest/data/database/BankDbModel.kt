package com.example.bintest.data.database

data class BankDbModel(
    val name: String,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)