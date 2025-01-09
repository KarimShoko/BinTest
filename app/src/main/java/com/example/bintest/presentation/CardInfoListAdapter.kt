package com.example.bintest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bintest.databinding.CardInfoItemBinding
import com.example.bintest.domain.entity.CardInfo


class CardInfoListAdapter : ListAdapter<CardInfo, CardInfoViewHolder>(CardInfoDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardInfoViewHolder {
        val binding = CardInfoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CardInfoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CardInfoViewHolder, position: Int) {
        val cardInfo = getItem(position)
        holder.bind(cardInfo)
    }
}