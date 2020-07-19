package com.fabiocati.howlongtobeatunofficial.model


class GameSearchModel {
    var gameName: String = ""
    var gameId: Int = 0
    var imageUrl: String = ""
    var mainStoryTime: String = "--"
    var mainStoryAndExtraTime: String = "--"
    var completionistTime: String = "--"
}

class GameDetailsModel {
    var gameName: String = ""
    val gameId: Int = 0
    var imageUrl: String = ""
    var mainStoryTime: String = "--"
    var mainStoryAndExtraTime: String = "--"
    var completionistTime: String = "--"
    var description: String = ""
    var developer: String = ""
    var playableOn: String = ""
    var releaseDateNA: String = ""
    var releaseDateEU: String = ""
    var releaseDateJP: String = ""
    var publisher: String = ""
    var genres: String = ""
    var updated: String = ""
    var coOpTime: String = ""
    var vsTime: String = ""
    var isOnlineGame = false
    val rating: Double = 94.0
    val reviews = ArrayList<Review>()
}