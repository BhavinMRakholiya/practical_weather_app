package com.practiclewatheraapp.data.repository

import com.practiclewatheraapp.domain.model.CityDetails
import com.practiclewatheraapp.source.dao.CityDao
import com.practiclewatheraapp.source.entity.CityData
import com.practiclewatheraapp.source.remote.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(private val mApiService: ApiServices, private val cityDao: CityDao) {
    suspend fun saveCityData(cityData: CityData):Long{
        return withContext(Dispatchers.IO) {
            return@withContext cityDao.insert(cityData)
        }
    }

    suspend fun getCityData():List<CityData>{
        return withContext(Dispatchers.IO) {
            return@withContext cityDao.getCityData()
        }
    }


    suspend fun fetchCityDetails(lat: String,lang:String,appId:String): Response<CityDetails> {
        return withContext(Dispatchers.IO) {
            return@withContext mApiService.fetchWeather(lat, lang,appId)
        }
    }

}