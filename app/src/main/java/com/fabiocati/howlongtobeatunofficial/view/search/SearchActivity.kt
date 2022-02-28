package com.fabiocati.howlongtobeatunofficial.view.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fabiocati.howlongtobeatunofficial.R
import com.fabiocati.howlongtobeatunofficial.viewmodels.SearchActivityVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search.*

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[SearchActivityVM::class.java] }
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
        val searchRecyclerView = findViewById<RecyclerView>(R.id.search_recycler_view).apply {
            layoutManager = GridLayoutManager(this@SearchActivity, 2)
        }
        searchRecyclerView.adapter = adapter
        //adapter.setOnClickListener { gameId, view -> startDetailsActivity(this, gameId, view) }
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
                                category_text_view.text = context.getString(R.string.results)
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
            if (gameList.isEmpty() && !this.isLoading()) {
                no_game_found_layout.visibility = View.VISIBLE
            } else {
                no_game_found_layout.visibility = View.INVISIBLE
            }
        })
        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) loading_layout.visibility = View.VISIBLE
            else loading_layout.visibility = View.INVISIBLE
        })
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun isLoading(): Boolean {
        return viewModel.isLoading.value ?: return false;
    }
}