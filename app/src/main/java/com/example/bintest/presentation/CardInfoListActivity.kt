package com.example.bintest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.bintest.R

class CardInfoListActivity : AppCompatActivity() {

    private lateinit var viewModel: CardInfoListViewModel
    private lateinit var cardInfoListAdapter: CardInfoListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card_info_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[CardInfoListViewModel::class.java]
        viewModel.cardInfoList.observe(this) {
            cardInfoListAdapter.submitList(it)//устаовка нового списка в ListAdapter
        }

    }

    private fun setupRecyclerView() {
        val rvCardInfoList = findViewById<RecyclerView>(R.id.rv_card_info_list)//recyclerView
        cardInfoListAdapter = CardInfoListAdapter()
        rvCardInfoList.adapter = cardInfoListAdapter
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, CardInfoListActivity::class.java)
            return intent
        }
    }
}


