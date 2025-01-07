package com.example.bintest.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bintest.R
import com.example.bintest.data.ApiFactory
import com.example.bintest.domain.CardInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val apiService = ApiFactory.apiService

    private lateinit var viewModel: MainViewModel


    private lateinit var tvSchemeValue: TextView
    private lateinit var tvBankValuePhone: TextView
    private lateinit var edBin: EditText
    private lateinit var buttonLookup: Button
    private lateinit var buttonQueryHistory: Button


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
        buttonLookup.setOnClickListener {
            val binNumber = edBin.text.toString().replace(" ", "")
            lifecycleScope.launch {
                val bin = withContext(Dispatchers.IO) {
                    apiService.getCardInfo(binNumber)
                }
                val cardInfo=CardInfo(binNumber= binNumber.toInt(),
                    scheme =  bin.scheme,
                    type =  bin.type, brand =  bin.brand,
                    prepaid =  bin.prepaid,
                    Country =  bin.Country,
                    Bank =  bin.Bank,
                    Number = bin.Number)
                viewModel.insertCardInfo(cardInfo)
                tvSchemeValue.text = bin.scheme
                tvBankValuePhone.text = bin.Bank?.phone
                Log.d("Test", bin.toString())
            }
        }
        buttonQueryHistory.setOnClickListener {
            val intent= CardInfoListActivity.newIntent(this)
            startActivity(intent)
        }


    }

    fun initView() {
        tvSchemeValue = findViewById(R.id.tv_scheme_value)
        tvBankValuePhone = findViewById(R.id.tv_bank_value_phone)
        edBin = findViewById(R.id.edit_text_bin)
        buttonLookup = findViewById(R.id.button_lookup)
        buttonQueryHistory=findViewById(R.id.button_query_history)

    }


}
