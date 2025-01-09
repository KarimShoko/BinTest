package com.example.bintest.di

import android.content.Context
import androidx.room.Room
import com.example.bintest.data.database.AppDatabase
import com.example.bintest.data.database.CardInfoDao
import com.example.bintest.data.network.ApiService
import com.example.cryptoapp.data.mapper.CardMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCardMapper(): CardMapper {
        return CardMapper()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://lookup.binlist.net/")
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "main.db"
        ).build()
    }

    @Provides
    fun provideCardInfoDao(database: AppDatabase): CardInfoDao {
        return database.cardInfoDao()
    }
}