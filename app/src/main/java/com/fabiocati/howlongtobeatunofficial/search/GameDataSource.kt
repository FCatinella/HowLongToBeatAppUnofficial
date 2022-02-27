package com.fabiocati.howlongtobeatunofficial.search

import com.fabiocati.howlongtobeatunofficial.Constants.BASE_URL
import com.fabiocati.howlongtobeatunofficial.model.GameSearchModel
import com.fabiocati.howlongtobeatunofficial.retrofit.HLTBService
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

class GameDataSource @Inject constructor(
    private val htlbService: HLTBService
) {
    fun searchGamesFromName(gameName: String): ArrayList<GameSearchModel> {
        var array = ArrayList<GameSearchModel>()
        try {
            val request = htlbService
                .getGameList(gameName)
            val data = request.execute()
            val body = data.body()
            array = parseSearchPage(body!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return array
    }

    private fun parseSearchPage(document: Document): ArrayList<GameSearchModel> {
        val array = ArrayList<GameSearchModel>()
        val site = document.body()
        val gameElements = site.getElementsByClass("back_darkish")
        for (gameElement in gameElements) {
            val game = GameSearchModel()
            val image = gameElement.getElementsByAttribute("src")[0]
            game.imageUrl = "${BASE_URL}${image.attr("src")}"
            val gameDetailsElement = gameElement.getElementsByClass("search_list_details")[0]
            val details = gameDetailsElement.getElementsByClass("shadow_text")[0].child(0)
            getGameTimes(game, gameDetailsElement.child(1))
            game.gameName = details.text()
            game.gameId = getId(details)
            array.add(game)
        }
        return array
    }

    private fun getId(element: Element): Int {
        val idString = element.attr("href")
        try {
            return idString.substringAfter("id=")
                .toInt()
        } catch (e: Exception) {
            return 0
        }
    }

    private fun getGameTimes(game: GameSearchModel, element: Element) {
        val children = element.children()[0].children()
        for (i in 0 until children.size step 2) {
            val child = children[i]
            when (child.text()) {
                "Main Story" -> {
                    game.mainStoryTime = children[i + 1].text()
                }
                "Main + Extra" -> {
                    game.mainStoryAndExtraTime = children[i + 1].text()
                }
                "Completionist" -> {
                    game.completionistTime = children[i + 1].text()
                }
            }
        }
    }
}
