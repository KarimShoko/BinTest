    package com.example.bintest.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.bintest.data.database.CardInfoDao
import com.example.bintest.data.network.ApiService
import com.example.bintest.domain.entity.CardInfo
import com.example.bintest.domain.CardRepository
import com.example.cryptoapp.data.mapper.CardMapper
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val mapper: CardMapper,
    private val cardInfoDao: CardInfoDao,
    private val apiService: ApiService,
) : CardRepository {

    override fun getCardInfoList(): LiveData<List<CardInfo>> {
        return cardInfoDao.getCardInfoList().map { mapper.mapListDbModelToListEntity(it) }
    }

    override suspend fun loadCardInfo(binNumber: String): CardInfo {
        val cardInfoDto = apiService.getCardInfo(binNumber)
        val cardInfo = mapper.mapDtoToEntity(cardInfoDto, binNumber)
        cardInfoDao.insertAndLimit(mapper.mapDtoToDbModel(cardInfoDto, binNumber))
        return cardInfo
    }
}