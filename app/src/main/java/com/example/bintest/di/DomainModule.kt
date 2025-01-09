package com.example.bintest.di

import com.example.bintest.data.database.CardInfoDao
import com.example.bintest.data.network.ApiService
import com.example.bintest.data.repository.CardRepositoryImpl
import com.example.bintest.domain.CardRepository
import com.example.cryptoapp.data.mapper.CardMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideRepository( mapper: CardMapper,
                           cardInfoDao: CardInfoDao,
                           apiService: ApiService): CardRepository {
        return CardRepositoryImpl(mapper, cardInfoDao, apiService)
    }
}