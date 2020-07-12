package com.fabiocati.howlongtobeatunofficial.details

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiocati.howlongtobeatunofficial.Constants
import com.fabiocati.howlongtobeatunofficial.GameRepository
import com.fabiocati.howlongtobeatunofficial.model.GameDetailsModel
import com.fabiocati.howlongtobeatunofficial.retrofit.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class DetailsActivityVM: ViewModel() {

    private var gameId : Int = 0
    private lateinit var _game : MutableLiveData<GameDetailsModel>
    lateinit var game: LiveData<GameDetailsModel>
    var gameRepo = GameRepository.getInstance()

    init {
        _game = MutableLiveData()
        game = _game
    }


    fun initFromBundle(bundle: Bundle?){
        bundle ?: return
        gameId = bundle.getInt(Constants.GAME_ID)
    }

    fun getGame(){
        viewModelScope.launch(Dispatchers.IO) {
            val game = gameRepo.getGame(gameId)
            _game.postValue(game)
        }
    }
}