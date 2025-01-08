package com.example.bintest.domain

import androidx.lifecycle.LiveData

class GetCardInfoListUseCase(private val repository: CardRepository) {
    operator fun invoke(): LiveData<List<CardInfo>> {
        return repository.getCardInfoList()
    }
}