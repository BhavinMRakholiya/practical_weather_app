package com.practiclewatheraapp.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.practiclewatheraapp.source.dao.CityDao
import com.practiclewatheraapp.source.entity.CityData

/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 * @Created by ZARA
 */
@Database(entities = [CityData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val cityDao: CityDao

    companion object {
        const val DB_NAME = "WeatherDatabase.db"
    }
}
