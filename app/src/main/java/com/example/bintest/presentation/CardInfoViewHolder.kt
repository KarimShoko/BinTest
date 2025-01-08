package com.example.bintest.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bintest.R

class CardInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvBinNumber = view.findViewById<TextView>(R.id.tv_bin_number)
    val tvScheme = view.findViewById<TextView>(R.id.tv_scheme_value)
    val tvType = view.findViewById<TextView>(R.id.tv_type_value)
    val tvBrand = view.findViewById<TextView>(R.id.tv_brand_value)
    val tvCountry = view.findViewById<TextView>(R.id.tv_country_value)
    val tvLatitude = view.findViewById<TextView>(R.id.tv_country_value_latitude)
    val tvLongitude = view.findViewById<TextView>(R.id.tv_country_value_longitude)
    val tvBankName = view.findViewById<TextView>(R.id.tv_bank_value_name)
    val tvBankUrl = view.findViewById<TextView>(R.id.tv_bank_value_url)
    val tvBankPhone = view.findViewById<TextView>(R.id.tv_bank_value_phone)
    val tvBankCity = view.findViewById<TextView>(R.id.tv_bank_value_city)

}