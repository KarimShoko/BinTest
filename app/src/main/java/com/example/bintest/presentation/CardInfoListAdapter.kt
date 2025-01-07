package com.example.bintest.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bintest.R
import com.example.bintest.domain.CardInfo


//1-тип,которым мы работаем,2=тип вью холдера,3-коллбек
class CardInfoListAdapter : ListAdapter<CardInfo, CardInfoViewHolder>(CardInfoDiffCallback) {

    //показывает,как создавать View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardInfoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_info_item, parent, false)
        return CardInfoViewHolder(view)
    }

    //показывает как вставить значение в view
    override fun onBindViewHolder(holder: CardInfoViewHolder, position: Int) {
        val cardInfo = getItem(position)
        holder.tvBinNumber.text = cardInfo.binNumber.toString()
        holder.tvScheme.text = cardInfo.scheme
        holder.tvType.text = cardInfo.type
        holder.tvBrand.text = cardInfo.brand
        holder.tvCountry.text = cardInfo.Country.name
        holder.tvLatitude.text = cardInfo.Country.latitude.toString()
        holder.tvLongitude.text = cardInfo.Country.longitude.toString()
        holder.tvBankName.text = cardInfo.Bank.name
        holder.tvBankPhone.text = cardInfo.Bank.phone
        holder.tvBankUrl.text = cardInfo.Bank.url
        holder.tvBankCity.text = cardInfo.Bank.city
    }

}