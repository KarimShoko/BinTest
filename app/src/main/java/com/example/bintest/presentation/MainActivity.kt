package com.example.bintest.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bintest.R
import com.example.bintest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addTextChangeListeners()
        observeViewModel()
        binding.buttonLookup.setOnClickListener {
            val binNumber = binding.etBin.text
            viewModel.loadCardInfo(binNumber.toString())
        }
        binding.buttonQueryHistory.setOnClickListener {
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
            binding.tilBinNumber.error = message
        }
        viewModel.errorHttp.observe(this){
            viewModel.errorHttp.observe(this) { isError ->
                if (isError == true) {
                    Toast.makeText(
                        this,
                        getString(R.string.error_http_message),
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.resetHttpError()
                }
            }
        }
        viewModel.cardInfoItem.observe(this) {
            with(binding) {
                tvTitle.visibility=View.VISIBLE
                tvSchemeValue.setTextAndShow(it.scheme)
                tvTypeValue.setTextAndShow(it.type)
                tvBrandValue.setTextAndShow(it.brand)
                tvCountryValue.setTextAndShow(it.country.name)
                tvCountryLatitudeValue.setTextAndShow(it.country.latitude.toString())
                tvCountryLongitudeValue.setTextAndShow(it.country.longitude.toString())
                tvBankNameValue.setTextAndShow(it.bank.name)
                tvBankUrlValue.setTextAndShow(it.bank.url)
                tvBankPhoneValue.setTextAndShow(it.bank.phone)
                tvBankCityValue.setTextAndShow(it.bank.city)
                tvCountryCurrencyValue.setTextAndShow(it.country.currency)

                tvSchemeLabel.visibility = View.VISIBLE
                tvTypeLabel.visibility = View.VISIBLE
                tvBrandLabel.visibility = View.VISIBLE
                tvCountryLabel.visibility = View.VISIBLE
                tvCountryLatitudeLabel.visibility = View.VISIBLE
                tvCountryLongitudeValue.visibility = View.VISIBLE
                tvCountryCurrencyLabel.visibility = View.VISIBLE
                tvBankLabel.visibility = View.VISIBLE
                tvCountryLongitudeLabel.visibility=View.VISIBLE
                Log.d("Test", it.toString())
            }
        }
    }

    private fun addTextChangeListeners() {
        binding.etBin.addTextChangedListener(object : TextWatcher {
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

    fun TextView.setTextAndShow(value: String?) {
        this.text = value
        this.visibility = View.VISIBLE
    }
}
//22022061