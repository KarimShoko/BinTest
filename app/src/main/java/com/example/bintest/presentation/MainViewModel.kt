package com.example.bintest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bintest.data.repository.CardRepositoryImpl
import com.example.bintest.domain.CardInfo
import com.example.bintest.domain.LoadCardInfoUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) :AndroidViewModel(application) {

    private val repositry=CardRepositoryImpl(application)

    private val loadCardInfoUseCase=LoadCardInfoUseCase(repositry)

    private val _cardInfoItem = MutableLiveData<CardInfo>()//
    val  cardInfoItem: LiveData<CardInfo>
        get() = _cardInfoItem

    fun loadCardInfo(binNumber:String){
        viewModelScope.launch {
           val cardInfo= loadCardInfoUseCase(binNumber)
            _cardInfoItem.value=cardInfo

        }
    }
}