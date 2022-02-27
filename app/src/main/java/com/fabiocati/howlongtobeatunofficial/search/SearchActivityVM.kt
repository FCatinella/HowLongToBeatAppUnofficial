package com.fabiocati.howlongtobeatunofficial.search

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiocati.howlongtobeatunofficial.GameRepository
import com.fabiocati.howlongtobeatunofficial.model.GameSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchActivityVM @Inject constructor(
    private val gameRepo: GameRepository
) : ViewModel() {

    private var gameName: String = ""
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    private var _searchResults: MutableLiveData<ArrayList<GameSearchModel>> = MutableLiveData()
    var searchResults: LiveData<ArrayList<GameSearchModel>> = _searchResults

    fun initViewModelFromBundle(bundle: Bundle?) {
        bundle ?: return
    }

    fun searchGame(gameName: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _searchResults.postValue(ArrayList())
            val result = gameRepo.searchGame(gameName)
            delay(1000)
            _isLoading.postValue(false)
            _searchResults.postValue(result)
        }
    }

    fun getBackground(): String {
        return gameRepo.getBackground()
    }
}