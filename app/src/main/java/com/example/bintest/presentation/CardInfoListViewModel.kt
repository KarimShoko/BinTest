package com.example.bintest.presentation

import androidx.lifecycle.ViewModel
import com.example.bintest.domain.usecases.GetCardInfoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardInfoListViewModel @Inject constructor(private val getCardInfoListUseCase: GetCardInfoListUseCase) : ViewModel() {
    val cardInfoList = getCardInfoListUseCase()
}