package com.fabiocati.howlongtobeatunofficial

import android.app.Activity
import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.fabiocati.howlongtobeatunofficial.details.DetailsActivity
import com.fabiocati.howlongtobeatunofficial.search.SearchActivity

fun startDetailsActivity(activity: Activity, gameId: Int, gameCoverView: View) {
    val intent = Intent(activity, DetailsActivity::class.java)
    intent.putExtra(Constants.GAME_ID, gameId)
    val options = ActivityOptions
        .makeSceneTransitionAnimation(activity, gameCoverView, "game_cover")
    // start the new activity
    activity.startActivity(intent, options.toBundle())
}

fun startSearchActivity(activity: Activity, gameName: String) {
    val intent = Intent(activity, SearchActivity::class.java)
    intent.putExtra(Constants.GAME_NAME, gameName)
    activity.startActivity(intent)
}