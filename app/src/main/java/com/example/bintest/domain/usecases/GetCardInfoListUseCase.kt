package com.example.bintest.domain.usecases

import androidx.lifecycle.LiveData
import com.example.bintest.domain.CardRepository
import com.example.bintest.domain.entity.CardInfo

class GetCardInfoListUseCase (private val repository: CardRepository) {
    operator fun invoke(): LiveData<List<CardInfo>> {
        return repository.getCardInfoList()
    }
}