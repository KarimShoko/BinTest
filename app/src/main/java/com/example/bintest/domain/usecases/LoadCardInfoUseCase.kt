package com.example.bintest.domain.usecases

import com.example.bintest.domain.CardRepository
import com.example.bintest.domain.entity.CardInfo

class LoadCardInfoUseCase(private val repository: CardRepository) {
    suspend operator fun invoke(binNumber: String): CardInfo {
        return repository.loadCardInfo(binNumber)
    }
}