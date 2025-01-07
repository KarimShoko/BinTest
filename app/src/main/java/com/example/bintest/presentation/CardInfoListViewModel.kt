package com.example.bintest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bintest.data.AppDatabase

class CardInfoListViewModel(application: Application) :AndroidViewModel(application) {
    private val db= AppDatabase.getInstance(application).coinPriceInfoDao()

    val cardInfoList=db.getCardInfoList()


}