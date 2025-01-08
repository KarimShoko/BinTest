package com.example.bintest.domain

import androidx.lifecycle.LiveData

interface CardRepository {
    fun getCardInfoList(): LiveData<List<CardInfo>>
    suspend fun loadCardInfo(binNumber: String): CardInfo
}