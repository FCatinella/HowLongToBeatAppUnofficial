package com.fabiocati.howlongtobeatunofficial.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.fabiocati.howlongtobeatunofficial.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity: AppCompatActivity() {
    private val viewModel = DetailsActivityVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if(intent != null){
            viewModel.initFromBundle(intent.extras)
        } else {
            viewModel.initFromBundle(savedInstanceState)
        }
        observeViewModel()
        viewModel.getGame()
    }

    private fun observeViewModel() {
        viewModel.game.observe(this, Observer { game ->
            Glide.with(this)
                .load(game.imageUrl)
                .into(game_cover_imageview)
            game_title_textview.text = game.gameName
            game_description_textview.text = game.description
            game_mainTime_textview.text = game.mainStoryTime
        })
    }
}