package com.fabiocati.howlongtobeatunofficial.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.fabiocati.howlongtobeatunofficial.R
import com.fabiocati.howlongtobeatunofficial.startDetailsActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity: AppCompatActivity() {
    var viewModel = SearchActivityVM()
    var adapter = SearchActivityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (intent != null) {
            viewModel.initViewModelFromBundle(intent.extras)
        } else {
            viewModel.initViewModelFromBundle(savedInstanceState)
        }
        search_recycler_view.adapter = adapter
        adapter.setOnClickListener { gameId -> startDetailsActivity(this, gameId) }
        observeViewModel()
        viewModel.searchGame()
    }

    private fun observeViewModel() {
        viewModel.searchResults.observe(this, Observer { gameList ->
            adapter.setDataSet(gameList)
        })
    }
}