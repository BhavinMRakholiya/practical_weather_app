package com.practiclewatheraapp.source.remote

import com.practiclewatheraapp.domain.model.CityDetails
import com.practiclewatheraapp.source.entity.CityData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("data/2.5/weather")
    suspend fun fetchWeather(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("appid") appId: String
    ): Response<CityDetails>
}