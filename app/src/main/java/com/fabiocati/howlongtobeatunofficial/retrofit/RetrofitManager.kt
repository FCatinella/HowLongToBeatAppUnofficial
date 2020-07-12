package com.fabiocati.howlongtobeatunofficial.retrofit

import retrofit2.Retrofit

class RetrofitManager {
    companion object {
        private const val BASE_URL = "https://howlongtobeat.com"
        private var instance: Retrofit? = null

        private fun getRetrofit(): Retrofit {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JsoupConverterFactory)
                    .build()
            }
            return instance!!
        }

        fun getHTLBService() : HLTBService {
            return getRetrofit().create(HLTBService::class.java)
        }

    }

}