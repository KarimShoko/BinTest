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
                    _errorInputBin.value = true // Отобразить ошибку пользователю
                } catch (e: Exception) {
                    Log.e("ViewModel", "Unexpected Error: ${e.message}")
                    _errorInputBin.value = true
                }
            }
        }
    }

    private fun parseBin(inputBin: String?): String {
        //если inputText не равен null, тогда вернем этот inputName и уберем пробелы,а если равен-вернем пустую строку
        return inputBin?.replace(" ", "") ?: ""
    }

    //проверка корректности данных
    private fun validateInput(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputBin.value = true
        }
        return result
    }

    fun resetErrorInputName() {//сброс ошибки ввода имени
        _errorInputBin.value = false
    }
}