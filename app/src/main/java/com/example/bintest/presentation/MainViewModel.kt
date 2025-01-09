package com.example.bintest.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bintest.data.repository.CardRepositoryImpl
import com.example.bintest.domain.CardInfo
import com.example.bintest.domain.LoadCardInfoUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repositry = CardRepositoryImpl(application)

    private val loadCardInfoUseCase = LoadCardInfoUseCase(repositry)

    private val _errorInputBin = MutableLiveData<Boolean>()//для ошибок ввода имени
    val errorInputBin: LiveData<Boolean>
        get() = _errorInputBin

    private val _errorHttp = MutableLiveData<Boolean>()//для ошибок ввода имени
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