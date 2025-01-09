package com.example.bintest.domain

import androidx.lifecycle.LiveData
import com.example.bintest.domain.entity.CardInfo

interface CardRepository {
    fun getCardInfoList(): LiveData<List<CardInfo>>
    suspend fun loadCardInfo(binNumber: String): CardInfo
}