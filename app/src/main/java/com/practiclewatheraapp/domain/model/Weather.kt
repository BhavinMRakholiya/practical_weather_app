package com.practiclewatheraapp.domain.model

import com.google.gson.annotations.SerializedName


data class Weather(
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,

)