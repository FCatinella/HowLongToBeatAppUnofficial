package com.fabiocati.howlongtobeatunofficial.search

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiocati.howlongtobeatunofficial.Constants
import com.fabiocati.howlongtobeatunofficial.GameRepository
import com.fabiocati.howlongtobeatunofficial.model.GameSearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivityVM: ViewModel() {
    private val gameRepo = GameRepository.getInstance()
    private var gameName: String = ""

    private var _searchResults: MutableLiveData<ArrayList<GameSearchModel>>
    var searchResults: LiveData<ArrayList<GameSearchModel>>

    init {
        _searchResults = MutableLiveData()
        searchResults = _searchResults
    }

    fun initViewModelFromBundle(bundle: Bundle?) {
        bundle ?: return
    }

    fun searchGame(gameName: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val result = gameRepo.searchGame(gameName)
            _searchResults.postValue(result)
        }
    }
}