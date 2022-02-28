package com.fabiocati.howlongtobeatunofficial.data.repositories

import com.fabiocati.howlongtobeatunofficial.Constants
import com.fabiocati.howlongtobeatunofficial.data.datasources.GameDetailsDataSource
import com.fabiocati.howlongtobeatunofficial.data.model.GameDetailsModel
import com.fabiocati.howlongtobeatunofficial.data.model.GameSearchModel
import com.fabiocati.howlongtobeatunofficial.data.datasources.GameDataSource
import javax.inject.Inject
import kotlin.random.Random

class GameRepository @Inject constructor(
    private val gameSearchDataSource: GameDataSource,
    private val gameDetailsDataSource: GameDetailsDataSource
) {

    private val cacheMap = HashMap<Int, GameDetailsModel>()

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

    fun getBackground(): String {
        val random = Random.nextInt(0, Constants.GAME_BACK_LINKS.size)
        return Constants.GAME_BACK_LINKS[random]
    }
}