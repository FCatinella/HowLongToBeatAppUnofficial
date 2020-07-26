package com.fabiocati.howlongtobeatunofficial.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fabiocati.howlongtobeatunofficial.R
import com.fabiocati.howlongtobeatunofficial.model.GameSearchModel
import kotlinx.android.synthetic.main.element_game_search.view.*

class SearchActivityAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataSet = ArrayList<GameSearchModel>()

    private class GameSearchViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView)

    private var onClickFunction: (Int) -> Any? = { }

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

    fun setOnClickListener(function: (Int) -> Any?) {
        onClickFunction = function
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val game = dataSet[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(game.imageUrl)
                .override(400,400)
                .into(game_cover_imageview)
            game_mainTime_hours_textview.text = game.mainStoryTime
            game_ExtraTime_hours_textview.text = game.mainStoryAndExtraTime
            game_completionistTime_hours_textview.text = game.completionistTime
            game_title_textview.text = game.gameName
            setOnClickListener {
                onClickFunction(game.gameId)
            }
        }
    }
}