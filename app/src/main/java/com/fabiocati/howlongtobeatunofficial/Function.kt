package com.fabiocati.howlongtobeatunofficial

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import com.fabiocati.howlongtobeatunofficial.details.DetailsActivity
import com.fabiocati.howlongtobeatunofficial.search.SearchActivity

fun startDetailsActivity(activity: Activity, gameId: Int) {
    val intent = Intent(activity, DetailsActivity::class.java)
    intent.putExtra(Constants.GAME_ID, gameId)
    activity.startActivity(intent)
}

fun startSearchActivity(activity: Activity, gameName: String) {
    val intent = Intent(activity, SearchActivity::class.java)
    intent.putExtra(Constants.GAME_NAME, gameName)
    activity.startActivity(intent)
}