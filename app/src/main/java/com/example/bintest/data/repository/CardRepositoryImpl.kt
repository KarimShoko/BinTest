package com.example.bintest.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.bintest.data.database.AppDatabase
import com.example.bintest.data.network.ApiFactory
import com.example.bintest.domain.CardInfo
import com.example.bintest.domain.CardRepository
import com.example.cryptoapp.data.mapper.CardMapper

class CardRepositoryImpl(application: Application) : CardRepository {

    private val mapper = CardMapper()
    private val cardInfoDao = AppDatabase.getInstance(application).cardInfoDao()
    private val apiService = ApiFactory.apiService

    override fun getCardInfoList(): LiveData<List<CardInfo>> {
        return cardInfoDao.getCardInfoList().map { mapper.mapListDbModelToListEntity(it) }
    }


    override suspend fun loadCardInfo(binNumber: String): CardInfo {
        val cardInfoDto = apiService.getCardInfo(binNumber)
        val cardInfo = mapper.mapDtoToEntity(cardInfoDto, binNumber)
        val cardInfoDbModel =
            cardInfoDao.insertCardInfo(mapper.mapDtoToDbModel(cardInfoDto, binNumber))
        return cardInfo
    }
}