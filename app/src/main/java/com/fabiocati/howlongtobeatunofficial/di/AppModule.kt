package com.fabiocati.howlongtobeatunofficial.di

import com.fabiocati.howlongtobeatunofficial.Constants.BASE_URL
import com.fabiocati.howlongtobeatunofficial.data.repositories.GameRepository
import com.fabiocati.howlongtobeatunofficial.data.datasources.GameDetailsDataSource
import com.fabiocati.howlongtobeatunofficial.retrofit.HLTBService
import com.fabiocati.howlongtobeatunofficial.retrofit.JsoupConverterFactory
import com.fabiocati.howlongtobeatunofficial.data.datasources.GameDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHLTBService(): HLTBService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(JsoupConverterFactory)
        .build().create(HLTBService::class.java)


    @Singleton
    @Provides
    fun provideGameRepository(
        gameDetailsDataSource: GameDetailsDataSource,
        gameDataSource: GameDataSource
    ): GameRepository = GameRepository(
        gameDataSource,
        gameDetailsDataSource
    )

    @Provides
    fun provideGameDetailsDataSource(service: HLTBService): GameDetailsDataSource =
        GameDetailsDataSource(service)

    @Provides
    fun provideGameDataSource(service: HLTBService): GameDataSource = GameDataSource(service)
}