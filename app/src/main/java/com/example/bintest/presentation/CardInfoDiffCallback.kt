package com.example.bintest.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.bintest.domain.CardInfo

object CardInfoDiffCallback : DiffUtil.ItemCallback<CardInfo>() {

    override fun areItemsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem.binNumber == newItem.binNumber
    }

    override fun areContentsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem == newItem
    }
}