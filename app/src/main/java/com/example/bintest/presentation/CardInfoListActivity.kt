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
import com.example.bintest.databinding.ActivityCardInfoListBinding
import com.example.bintest.databinding.ActivityMainBinding

class CardInfoListActivity : AppCompatActivity() {

    private lateinit var viewModel: CardInfoListViewModel
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
        viewModel = ViewModelProvider(this)[CardInfoListViewModel::class.java]
        viewModel.cardInfoList.observe(this) {
            cardInfoListAdapter.submitList(it)//устаовка нового списка в ListAdapter
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


