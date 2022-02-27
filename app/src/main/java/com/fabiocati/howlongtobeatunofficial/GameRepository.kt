package com.fabiocati.howlongtobeatunofficial

import com.fabiocati.howlongtobeatunofficial.details.GameDetailsDataSource
import com.fabiocati.howlongtobeatunofficial.model.GameDetailsModel
import com.fabiocati.howlongtobeatunofficial.model.GameSearchModel
import com.fabiocati.howlongtobeatunofficial.search.GameDataSource
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