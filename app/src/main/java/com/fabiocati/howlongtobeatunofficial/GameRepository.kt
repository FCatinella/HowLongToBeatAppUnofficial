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
            "https://www.cyberludus.com/content/uploads/2018/04/godofwar_wall.jpg", //God of war
            "https://www.optimagazine.com/wp-content/uploads/2019/02/resident-evil-2-remake-recensione.jpg", // RE 2 remake
            "https://gametimes.com.br/wp-content/uploads/2020/04/wh3h15zelyg41-scaled.jpg", //Tlou 2
            "https://images.alphacoders.com/789/thumb-1920-789452.jpg", //Breath of the wild
            "https://www.cyberludus.com/content/uploads/2018/11/750703.jpg", //Rdd2
            "https://images.pushsquare.com/96299803f0808/demons-souls-ps5.original.jpg", // Demon's souls
            "https://images.hdqwalls.com/download/cyberpunk-2077-johnny-silverhand-4k-lt-3840x2160.jpg", // Cyberpunk 2077
            "https://cs10.pikabu.ru/post_img/big/2020/04/11/2/1586567698133226382.jpg" // FFVII remake
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