package com.example.bintest.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.bintest.databinding.CardInfoItemBinding
import com.example.bintest.domain.entity.CardInfo

class CardInfoViewHolder(private val binding: CardInfoItemBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(cardInfo: CardInfo) {
        binding.tvBinNumber.text = cardInfo.binNumber.toString()
        binding.tvSchemeValue.text = cardInfo.scheme
        binding.tvTypeValue.text = cardInfo.type
        binding.tvBrandValue.text = cardInfo.brand
        binding.tvCountryValue.text = cardInfo.country.name
        binding.tvCountryLatitudeValue.text = cardInfo.country.latitude.toString()
        binding.tvCountryLongitudeValue.text = cardInfo.country.longitude.toString()
        binding.tvBankNameValue.text = cardInfo.bank.name
        binding.tvBankUrlValue.text = cardInfo.bank.url
        binding.tvBankPhoneValue.text = cardInfo.bank.phone
        binding.tvBankCityValue.text = cardInfo.bank.city
        binding.tvCountryCurrencyValue.text = cardInfo.country.currency
    }
}