package com.example.bintest.domain

class LoadCardInfoUseCase(private val repository: CardRepository) {
    suspend operator fun invoke(binNumber: String): CardInfo {
        return repository.loadCardInfo(binNumber)
    }
}