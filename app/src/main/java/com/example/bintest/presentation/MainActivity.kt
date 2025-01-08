package com.example.bintest.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
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
    private lateinit var tvCountryCurrencyValue: TextView

    private lateinit var tvTitle: TextView
    private lateinit var tvSchemeLabel: TextView
    private lateinit var tvTypeLabel: TextView
    private lateinit var tvBrandLabel: TextView
    private lateinit var tvCountryLabel: TextView
    private lateinit var tvLatitudeLabel: TextView
    private lateinit var tvLongitudeLabel: TextView
    private lateinit var tvCurrencyLabel: TextView
    private lateinit var tvBankLabel: TextView


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

    private fun observeViewModel() {
        viewModel.errorInputBin.observe(this) {
            val message = if (it == true) {
                getString(R.string.error_invalid_bin)
            } else {
                null
            }
            tilBinNumber.error = message
        }
        viewModel.cardInfoItem.observe(this) {
            tvSchemeValue.setTextAndShow(it.scheme)
            tvTypeValue.setTextAndShow(it.type)
            tvBrandValue.setTextAndShow(it.brand)
            tvCountryValue.setTextAndShow(it.country.name)
            tvLatitudeValue.setTextAndShow(it.country.latitude.toString())
            tvLongitudeValue.setTextAndShow(it.country.longitude.toString())
            tvBankNameValue.setTextAndShow(it.bank.name)
            tvBankUrlValue.setTextAndShow(it.bank.url)
            tvBankPhoneValue.setTextAndShow(it.bank.phone)
            tvBankCityValue.setTextAndShow(it.bank.city)

            tvSchemeLabel.visibility = View.VISIBLE
            tvTypeLabel.visibility = View.VISIBLE
            tvBrandLabel.visibility = View.VISIBLE
            tvCountryLabel.visibility = View.VISIBLE
            tvLatitudeLabel.visibility = View.VISIBLE
            tvLongitudeLabel.visibility = View.VISIBLE
            tvCurrencyLabel.visibility = View.VISIBLE
            tvBankLabel.visibility = View.VISIBLE
            Log.d("Test", it.toString())
        }
    }

    private fun addTextChangeListeners() {
        etBinNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.resetErrorInputName()
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
        tvLatitudeValue = findViewById(R.id.tv_country_latitude_value)
        tvLongitudeValue = findViewById(R.id.tv_country_longitude_value)
        tvBankNameValue = findViewById(R.id.tv_bank_value_name)
        tvBankUrlValue = findViewById(R.id.tv_bank_value_url)
        tvBankPhoneValue = findViewById(R.id.tv_bank_value_phone)
        tvBankCityValue = findViewById(R.id.tv_bank_value_city)
        tvCountryCurrencyValue = findViewById(R.id.tv_country_currency_value)

        tvTitle = findViewById(R.id.tv_title)
        tvSchemeLabel = findViewById(R.id.tv_scheme_label)
        tvTypeLabel = findViewById(R.id.tv_type_label)
        tvBrandLabel = findViewById(R.id.tv_brand_label)
        tvCountryLabel = findViewById(R.id.tv_country_label)
        tvLatitudeLabel = findViewById(R.id.tv_country_latitude_label)
        tvLongitudeLabel = findViewById(R.id.tv_country_longitude_label)
        tvCurrencyLabel = findViewById(R.id.tv_country_currency_label)
        tvBankLabel = findViewById(R.id.tv_bank_label)

    }

    fun TextView.setTextAndShow(value: String?) {
        this.text = value
        this.visibility = View.VISIBLE
    }
}
//2202 2061