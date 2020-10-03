package com.fabiocati.howlongtobeatunofficial.search

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiocati.howlongtobeatunofficial.GameRepository
import com.fabiocati.howlongtobeatunofficial.model.GameSearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchActivityVM : ViewModel() {
    private val gameRepo = GameRepository.getInstance()
    private var gameName: String = ""

    var isLoading: LiveData<Boolean>
    private var _isLoading: MutableLiveData<Boolean>

    private var _searchResults: MutableLiveData<ArrayList<GameSearchModel>>
    var searchResults: LiveData<ArrayList<GameSearchModel>>

    init {
        _isLoading = MutableLiveData(false)
        isLoading = _isLoading
        _searchResults = MutableLiveData()
        searchResults = _searchResults
    }

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