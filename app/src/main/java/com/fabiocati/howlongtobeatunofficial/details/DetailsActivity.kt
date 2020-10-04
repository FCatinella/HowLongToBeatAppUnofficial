package com.fabiocati.howlongtobeatunofficial.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.fabiocati.howlongtobeatunofficial.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity: AppCompatActivity() {
    private val viewModel = DetailsActivityVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportPostponeEnterTransition()
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
            val gameCoverImageView = findViewById<ImageView>(R.id.game_cover_imageview)
            gameCoverImageView.transitionName = "game_cover"
            Glide.with(this)
                .load(game.imageUrl)
                .addListener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(gameCoverImageView)
            game_title_textview.text = game.gameName
            game_producer_textview.text = game.developer
            game_description_textview.text = game.description
            game_mainTime_hours_textview.text = game.mainStoryTime
            game_ExtraTime_hours_textview.text = game.mainStoryAndExtraTime
            game_completionistTime_hours_textview.text = game.completionistTime
            eu_release_textview.text = game.releaseDateEU
            na_release_textview.text = game.releaseDateNA
            jp_release_textview.text = game.releaseDateJP
        })
        viewModel.isLoading.observe(this, Observer { isLoading ->

        })
    }
}