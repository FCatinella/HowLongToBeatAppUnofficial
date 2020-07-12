package com.fabiocati.howlongtobeatunofficial.model


class GameSearchModel {
    var gameName: String = "Zelda Breath of the Wild"
    var gameId: Int = 0
    var imageUrl: String = "https://howlongtobeat.com/games/38019_The_Legend_of_Zelda_Breath_of_the_Wild.jpg"
    var mainStoryTime: String = "50 Hours"
    var mainStoryAndExtraTime: String = "93 1/2 Hours"
    var completionistTime: String = "184 Hours"
}

class GameDetailsModel {
    var gameName: String = "Zelda Breath of the Wild"
    val gameId: Int = 0
    var imageUrl: String = "https://howlongtobeat.com/games/38019_The_Legend_of_Zelda_Breath_of_the_Wild.jpg"
    var mainStoryTime: String = "50 Hours"
    var mainStoryAndExtraTime: String = "93 1/2 Hours"
    var completionistTime: String = "184 Hours"
    var description: String = "Forget everything you know about The Legend of Zelda games. Step into a world of discovery, exploration and adventure in The Legend of Zelda: Breath of the Wild, a boundary-breaking new game in the acclaimed series. Travel across fields, through forests and to mountain peaks as you discover what has become of the ruined kingdom of Hyrule in this stunning open-air adventure."
    var developer: String = "Nintendo"
    var playableOn: String = "Nintendo Switch, Wii U"
    var releaseDateNA: String = "March 03, 2017"
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