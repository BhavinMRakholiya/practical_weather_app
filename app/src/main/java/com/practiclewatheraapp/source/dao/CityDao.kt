package com.practiclewatheraapp.source.dao

import android.service.autofill.UserData
import androidx.room.*
import com.practiclewatheraapp.source.entity.CityData

@Dao
interface CityDao {
    @Insert
    suspend fun insert(cityData: CityData): Long

    @Transaction
    @Query("SELECT * FROM city_table ORDER BY id DESC")
    fun getCityData() : List<CityData>
}