package com.fabiocati.howlongtobeatunofficial.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.fabiocati.howlongtobeatunofficial.R
import com.fabiocati.howlongtobeatunofficial.startDetailsActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity: AppCompatActivity() {
    var viewModel = SearchActivityVM()
    var adapter = SearchActivityAdapter()
    private var animated = false

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
        search_bar.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus && !animated) search_motion_layout.transitionToEnd()
            animated = true
        }
        search_button.setOnClickListener {
            val gameToSearch = search_bar.text.toString()
            viewModel.searchGame(gameToSearch)
            category_text_view.text = "Risultati"
            closeKeyboard()
            search_bar.clearFocus()
        }
        observeViewModel()
        viewModel.searchGame()
    }

    override fun onBackPressed() {
        if (animated) {
            search_bar.clearFocus()
            search_motion_layout.transitionToStart()
            animated = false
        } else {
            super.onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewModel.searchResults.observe(this, Observer { gameList ->
            adapter.setDataSet(gameList)
        })
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}