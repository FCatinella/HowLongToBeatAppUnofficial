package com.fabiocati.howlongtobeatunofficial

import com.fabiocati.howlongtobeatunofficial.details.GameDetailsDataSource
import com.fabiocati.howlongtobeatunofficial.model.GameDetailsModel
import com.fabiocati.howlongtobeatunofficial.model.GameSearchModel
import com.fabiocati.howlongtobeatunofficial.search.GameDataSource
import kotlin.random.Random

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

        private val gameBackLink = arrayOf(
            "https://www.cyberludus.com/content/uploads/2018/04/godofwar_wall.jpg",
            "https://d.newsweek.com/en/full/1304688/claire-leon-vs-first-resident-evil-2-remake.jpg?w=1600&h=900&q=88&f=d569267390d26951a3b737d747184815",
            "https://www.playstationzone.it/wp-content/uploads/2020/06/The-Last-of-Us%E2%84%A2-Parte-II_20200619182952.jpg",
            "https://ae01.alicdn.com/kf/HTB1gOGdaizxK1RkSnaVq6xn9VXa2/Diamond-painting-Cartoon-The-Legend-Of-Zelda-Breath-Of-The-Wild-Wall-Art-Picture-Art-Print.jpg_640x640.jpg",
            "https://www.cyberludus.com/content/uploads/2018/11/750703.jpg",
            "https://images3.alphacoders.com/104/thumb-1920-1040112.png"
        )
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

    fun getBackground(): String {
        val random = Random.nextInt(0, gameBackLink.size)
        return gameBackLink[random]
    }
}