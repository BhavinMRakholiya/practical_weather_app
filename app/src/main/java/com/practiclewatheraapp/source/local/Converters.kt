package com.practiclewatheraapp.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.practiclewatheraapp.source.entity.CityData

class Converters {
    @TypeConverter
    fun listToJson(value: List<CityData>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<CityData>::class.java).toList()
}