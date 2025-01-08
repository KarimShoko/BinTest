package com.example.bintest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bintest.data.repository.CardRepositoryImpl
import com.example.bintest.domain.GetCardInfoListUseCase

class CardInfoListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CardRepositoryImpl(application)
    private val getCardInfoListUseCase = GetCardInfoListUseCase(repository)

    val cardInfoList = getCardInfoListUseCase()
}