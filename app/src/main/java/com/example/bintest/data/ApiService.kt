package com.example.bintest.data

import com.example.bintest.domain.CardInfo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @GET("{bin}")
    @Headers("Accept-Version: 3")
   suspend fun getCardInfo(@Path("bin") bin: String): CardInfo
}