package com.fabiocati.howlongtobeatunofficial.viewmodels

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiocati.howlongtobeatunofficial.Constants
import com.fabiocati.howlongtobeatunofficial.data.repositories.GameRepository
import com.fabiocati.howlongtobeatunofficial.data.model.GameDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsActivityVM @Inject constructor(
    private val gameRepo: GameRepository
) : ViewModel() {

    private var gameId: Int = 0
    private val _game: MutableLiveData<GameDetailsModel> = MutableLiveData()
    val game: LiveData<GameDetailsModel> = _game
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun initFromBundle(bundle: Bundle?) {
        bundle ?: return
        gameId = bundle.getInt(Constants.GAME_ID)
    }

    fun getGame() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val game = gameRepo.getGame(gameId)
            _game.postValue(game)
            _isLoading.postValue(false)
        }
    }
}