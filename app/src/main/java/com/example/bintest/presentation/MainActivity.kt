package com.example.bintest.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.bintest.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var tilBinNumber: TextInputLayout
    private lateinit var etBinNumber: TextInputEditText
    private lateinit var buttonLookup: Button
    private lateinit var buttonQueryHistory: Button
    private lateinit var tvSchemeValue: TextView
    private lateinit var tvTypeValue: TextView
    private lateinit var tvBrandValue: TextView
    private lateinit var tvCountryValue: TextView
    private lateinit var tvLatitudeValue: TextView
    private lateinit var tvLongitudeValue: TextView
    private lateinit var tvBankNameValue: TextView
    private lateinit var tvBankUrlValue: TextView
    private lateinit var tvBankPhoneValue: TextView
    private lateinit var tvBankCityValue: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initView()
        addTextChangeListeners()
        observeViewModel()
        buttonLookup.setOnClickListener {
            val binNumber = etBinNumber.text
            viewModel.loadCardInfo(binNumber.toString())
        }
        buttonQueryHistory.setOnClickListener {
            val intent = CardInfoListActivity.newIntent(this)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {//подписка
        viewModel.errorInputBin.observe(this) {//viewLifecycleOwner,тк ЖЦ view и фрагмента отличаются и фрагмент может жить дольше,чем view
            val message = if (it == true) {//если ошибка есть(true)
                getString(R.string.error_invalid_bin)//заносим в переменную данный строковый ресурс
            } else {
                null
            }
            tilBinNumber.error = message//устанавливаем ошибку в поле
        }
        viewModel.cardInfoItem.observe(this) {
            etBinNumber
            buttonLookup
            buttonQueryHistory
            tvSchemeValue
            tvTypeValue
            tvBrandValue
            tvCountryValue
            tvLatitudeValue
            tvLongitudeValue
            tvBankNameValue
            tvBankUrlValue
            tvBankPhoneValue
            tvBankCityValue
            Log.d("Test", it.toString())
        }
    }

    private fun addTextChangeListeners() {//слушатели текста(для ошибок)
        etBinNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {//когда текст был изменен
                viewModel.resetErrorInputName()//убираем ошибку
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun initView() {
        tilBinNumber = findViewById(R.id.til_bin_number)
        etBinNumber = findViewById(R.id.et_bin)
        buttonLookup = findViewById(R.id.button_lookup)
        buttonQueryHistory = findViewById(R.id.button_query_history)
        tvSchemeValue = findViewById(R.id.tv_scheme_value)
        tvTypeValue = findViewById(R.id.tv_type_value)
        tvBrandValue = findViewById(R.id.tv_brand_value)
        tvCountryValue = findViewById(R.id.tv_country_value)
        tvLatitudeValue = findViewById(R.id.tv_country_value_latitude)
        tvLongitudeValue = findViewById(R.id.tv_country_value_longitude)
        tvBankNameValue = findViewById(R.id.tv_bank_value_name)
        tvBankUrlValue = findViewById(R.id.tv_bank_value_url)
        tvBankPhoneValue = findViewById(R.id.tv_bank_value_phone)
        tvBankCityValue = findViewById(R.id.tv_bank_value_city)
    }
}
//2202 2061