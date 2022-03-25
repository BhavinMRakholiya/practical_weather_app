package com.practiclewatheraapp.domain.model

import com.google.gson.annotations.SerializedName


data class Wind(
    @SerializedName("speed")
    val speed: String,
    @SerializedName("deg")
    val deg: String,
    @SerializedName("gust")
    val gust: String,
)