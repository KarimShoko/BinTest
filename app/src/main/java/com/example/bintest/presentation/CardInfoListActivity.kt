package com.example.bintest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bintest.R
import com.example.bintest.databinding.ActivityCardInfoListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardInfoListActivity : AppCompatActivity() {

    private val viewModel: CardInfoListViewModel by viewModels()
    private lateinit var cardInfoListAdapter: CardInfoListAdapter

    private val binding by lazy {
        ActivityCardInfoListBinding.inflate(layoutInflater)
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
        setupRecyclerView()
        viewModel.cardInfoList.observe(this) {
            cardInfoListAdapter.submitList(it)
        }

    }

    private fun setupRecyclerView() {
        cardInfoListAdapter = CardInfoListAdapter()
        binding.rvCardInfoList.adapter = cardInfoListAdapter
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, CardInfoListActivity::class.java)
            return intent
        }
    }
}


