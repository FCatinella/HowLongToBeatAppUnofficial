package com.fabiocati.howlongtobeatunofficial.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fabiocati.howlongtobeatunofficial.R
import com.fabiocati.howlongtobeatunofficial.data.model.GameSearchModel

class SearchActivityAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataSet = ArrayList<GameSearchModel>()

    private class GameSearchViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    private var onClickFunction: (Int, View) -> Any? = { i: Int, view: View -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val from = LayoutInflater.from(parent.context)
        val view = from.inflate(R.layout.element_game_search, parent, false)
        return GameSearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setDataSet(array: ArrayList<GameSearchModel>) {
        dataSet = array
        notifyDataSetChanged()
    }

    fun setOnClickListener(function: (Int, View) -> Any?) {
        onClickFunction = function
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val game = dataSet[position]
        holder.itemView.apply {
            val gameCover = findViewById<ImageView>(R.id.game_cover_imageview).apply {
                transitionName = "game_cover"
            }
            Glide.with(this)
                .load(game.imageUrl)
                .override(400, 400)
                .into(gameCover)
            findViewById<TextView>(R.id.game_mainTime_hours_textview).text = game.mainStoryTime
            findViewById<TextView>(R.id.game_ExtraTime_hours_textview).text =
                game.mainStoryAndExtraTime
            findViewById<TextView>(R.id.game_completionistTime_hours_textview).text =
                game.completionistTime
            findViewById<TextView>(R.id.game_title_textview).text = game.gameName

            setOnClickListener {
                onClickFunction(game.gameId, gameCover)
            }
        }
    }
}