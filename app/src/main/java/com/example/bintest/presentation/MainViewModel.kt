package com.example.bintest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bintest.data.AppDatabase
import com.example.bintest.domain.CardInfo
import kotlinx.coroutines.launch

class MainViewModel(application: Application) :AndroidViewModel(application) {
    private val db=AppDatabase.getInstance(application).coinPriceInfoDao()


    fun insertCardInfo(cardInfo: CardInfo){
        viewModelScope.launch {
            db.insertCardInfoList(cardInfo)
        }
    }
}