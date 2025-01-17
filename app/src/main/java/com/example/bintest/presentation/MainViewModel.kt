package com.example.bintest.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bintest.domain.entity.CardInfo
import com.example.bintest.domain.usecases.LoadCardInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loadCardInfoUseCase: LoadCardInfoUseCase) : ViewModel() {

    private val _errorInputBin = MutableLiveData<Boolean>()
    val errorInputBin: LiveData<Boolean>
        get() = _errorInputBin

    private val _errorHttp = MutableLiveData<Boolean>()
    val errorHttp: LiveData<Boolean>
        get() = _errorHttp

    private val _cardInfoItem = MutableLiveData<CardInfo>()//
    val cardInfoItem: LiveData<CardInfo>
        get() = _cardInfoItem

    fun loadCardInfo(inputBin: String?) {
        val binNumber = parseBin(inputBin)
        val fieldsValid = validateInput(binNumber)
        if (fieldsValid) {
            viewModelScope.launch {
                try {
                    val cardInfo = loadCardInfoUseCase(binNumber)
                    _cardInfoItem.value = cardInfo
                } catch (e: HttpException) {
                    Log.e("ViewModel", "HTTP Error: ${e.code()} - ${e.message}")
                    _errorHttp.value=true
                } catch (e: Exception) {
                    Log.e("ViewModel", "Unexpected Error: ${e.message}")
                    _errorInputBin.value = true
                }
            }
        }
    }

    private fun parseBin(inputBin: String?): String {
        return inputBin?.replace(" ", "") ?: ""
    }


    private fun validateInput(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputBin.value = true
        }
        return result
    }

    fun resetHttpError() {
        _errorHttp.value = false
    }

    fun resetErrorInputName() {
        _errorInputBin.value = false
    }
}