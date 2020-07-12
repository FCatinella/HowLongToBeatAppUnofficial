package com.fabiocati.howlongtobeatunofficial

import com.fabiocati.howlongtobeatunofficial.details.GameDetailsDataSource
import com.fabiocati.howlongtobeatunofficial.model.GameDetailsModel
import com.fabiocati.howlongtobeatunofficial.model.GameSearchModel
import com.fabiocati.howlongtobeatunofficial.search.GameDataSource

class GameRepository private constructor() {

    companion object {
        private var instance: GameRepository? = null
        private var cacheMap = HashMap<Int, GameDetailsModel>()
        private val gameDetailsDataSource = GameDetailsDataSource()
        private val gameSearchDataSource = GameDataSource()

        fun getInstance(): GameRepository {
            if (instance == null) {
                instance = GameRepository()
            }
            return instance!!
        }
    }

    fun getGame(gameId: Int): GameDetailsModel {
        var game = cacheMap[gameId]
        if (game == null) {
            game = gameDetailsDataSource.getGameDetailsFromId(gameId)
        }
        return game
    }

    fun searchGame(gameName: String): ArrayList<GameSearchModel> {
        return gameSearchDataSource.searchGamesFromName(gameName)
    }
}