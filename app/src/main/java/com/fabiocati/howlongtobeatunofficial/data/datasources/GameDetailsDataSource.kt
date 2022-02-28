package com.fabiocati.howlongtobeatunofficial.data.datasources

import com.fabiocati.howlongtobeatunofficial.Constants.BASE_URL
import com.fabiocati.howlongtobeatunofficial.data.model.GameDetailsModel
import com.fabiocati.howlongtobeatunofficial.retrofit.HLTBService
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

class GameDetailsDataSource @Inject constructor(
    private val htlbService: HLTBService
) {

    private fun parsePageDetails(document: Document): GameDetailsModel {
        val site = document.body()
        val game = GameDetailsModel()
        game.gameName = getName(site)
        game.imageUrl = "${BASE_URL}${getImageUrl(site)}"
        getGameDescription(game, site)
        getTimes(game, site)
        return game
    }

    private fun getName(element: Element): String {
        return element.getElementsByClass("profile_header shadow_text")[0].text()
    }

    private fun getImageUrl(element: Element): String {
        return element.getElementsByClass("game_image desktop_hide")[0].getElementsByTag("img")
            .attr("src")
    }

    private fun getGameDescription(game: GameDetailsModel, element: Element) {
        val allText = element.getElementsByClass("in back_primary shadow_box")[2].text()
            .replace("...Read More", "")
        var description = allText.substringBefore("Developer")
        if (description == allText) description = "--"
        val infosElements = element.getElementsByClass("profile_info")
        for (infoElement in infosElements) {
            val splitted = infoElement.text()
                .split(":")
            when (splitted[0]) {
                "Developer" -> {
                    game.developer = splitted[1]
                }
                "Publisher" -> {
                    game.publisher = splitted[1]
                }
                "Playable On" -> {
                    game.playableOn = splitted[1]
                }
                "Genres" -> {
                    game.genres = splitted[1]
                }
                "NA" -> {
                    game.releaseDateNA = splitted[1]
                }
                "EU" -> {
                    game.releaseDateEU = splitted[1]
                }
                "JP" -> {
                    game.releaseDateJP = splitted[1]
                }
                "Updated" -> {
                    game.updated = splitted[1]
                }
            }
        }
        game.description = description
        return
    }

    private fun getTimes(game: GameDetailsModel, element: Element) {
        val timeElements = element.getElementsByClass("game_times")[0].child(0)
            .children()
        for (timeElement in timeElements) {
            val children = timeElement.children()
            when (children[0].text()) {
                "Main Story" -> {
                    game.mainStoryTime = children[1].text()
                }
                "Main + Extras" -> {
                    game.mainStoryAndExtraTime = children[1].text()
                }
                "Completionist" -> {
                    game.completionistTime = children[1].text()
                }
                "Co-Op" -> {
                    game.coOpTime = children[1].text()
                }
                "Vs." -> {
                    game.vsTime = children[1].text()
                }
            }
        }
    }

    fun getGameDetailsFromId(id: Int): GameDetailsModel {
        var game = GameDetailsModel()
        try {
            val request = htlbService
                .getGameDetails(id)
            val data = request.execute() //blocking
            val body = data.body()
            game = parsePageDetails(body!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return game
    }
}