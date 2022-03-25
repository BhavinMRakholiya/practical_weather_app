package com.practiclewatheraapp.domain.model

import com.google.gson.annotations.SerializedName


data class CityDetails(
    @SerializedName("weather")
    val weather: ArrayList<Weather>,
    @SerializedName("main")
    val main: Main,
    @SerializedName("wind")
    val wind: Wind,
)