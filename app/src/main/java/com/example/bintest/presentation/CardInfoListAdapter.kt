package com.example.bintest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bintest.R
import com.example.bintest.data.network.model.CardInfoDto
import com.example.bintest.domain.CardInfo



class CardInfoListAdapter : ListAdapter<CardInfo, CardInfoViewHolder>(CardInfoDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardInfoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_info_item, parent, false)
        return CardInfoViewHolder(view)
    }


    override fun onBindViewHolder(holder: CardInfoViewHolder, position: Int) {
        val cardInfo = getItem(position)
        holder.tvBinNumber.text = cardInfo.binNumber.toString()
        holder.tvScheme.text = cardInfo.scheme
        holder.tvType.text = cardInfo.type
        holder.tvBrand.text = cardInfo.brand
        holder.tvCountry.text = cardInfo.country.name
        holder.tvLatitude.text = cardInfo.country.latitude.toString()
        holder.tvLongitude.text = cardInfo.country.longitude.toString()
        holder.tvBankName.text = cardInfo.bank.name
        holder.tvBankPhone.text = cardInfo.bank.phone
        holder.tvBankUrl.text = cardInfo.bank.url
        holder.tvBankCity.text = cardInfo.bank.city
    }

}