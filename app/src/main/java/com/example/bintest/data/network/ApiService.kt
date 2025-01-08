package com.example.bintest.data.network

import com.example.bintest.data.network.model.CardInfoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @GET("{bin}")
    @Headers("Accept-Version: 3")
    suspend fun getCardInfo(@Path("bin") binNumber: String): CardInfoDto
}
