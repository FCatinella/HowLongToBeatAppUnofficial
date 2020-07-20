package com.fabiocati.howlongtobeatunofficial.search

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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
        Glide.with(this)
            .load(viewModel.getBackground())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(background)
        search_recycler_view.adapter = adapter
        adapter.setOnClickListener { gameId -> startDetailsActivity(this, gameId) }
        search_bar.apply {
            this.findViewById<EditText>(R.id.search_edit_text)
                .apply {
                    onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus && !animated) search_motion_layout.transitionToEnd()
                        animated = true
                    }
                    setOnEditorActionListener { _, actionId, _ ->
                        return@setOnEditorActionListener when (actionId) {
                            EditorInfo.IME_ACTION_SEARCH -> {
                                val gameToSearch = this.text.toString()
                                viewModel.searchGame(gameToSearch)
                                category_text_view.text = "Risultati"
                                closeKeyboard()
                                clearFocus()
                                true
                            }
                            else -> false
                        }
                    }
                }
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