package com.practiclewatheraapp.domain.model

import com.google.gson.annotations.SerializedName


data class Main(
    @SerializedName("temp")
    val temp: String,
    @SerializedName("feel_like")
    val feelLike: String,
    @SerializedName("temp_min")
    val tempMin: String,
    @SerializedName("temp_max")
    val tempMax: String,
    @SerializedName("humidity")
    val humidity: String,
)