package com.fabiocati.howlongtobeatunofficial.retrofit

import okhttp3.ResponseBody
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.http.*

interface HLTBService {
    @GET("/game")
    fun getGameDetails(@Query("id") gameId: Int): Call<Document>

    @FormUrlEncoded
    @POST("/search_results.php?page=1")
    fun getGameList(
        @Field("queryString") queryString: String,
        @Field("t") t: String = "games",
        @Field("sorthead") sorthead: String = "popular", @Field("sortd") sortd: String = "Normal Order"): Call<Document>

}
