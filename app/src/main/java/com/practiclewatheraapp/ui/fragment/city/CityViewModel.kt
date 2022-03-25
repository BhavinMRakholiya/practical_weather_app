package com.practiclewatheraapp.ui.fragment.city

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practiclewatheraapp.data.repository.WeatherRepositoryImp
import com.practiclewatheraapp.domain.model.CityDetails
import com.practiclewatheraapp.source.entity.CityData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val weatherRepositoryImp: WeatherRepositoryImp) :
    ViewModel() {
    private val job = CoroutineScope(Job() + Dispatchers.Main)
    private val cityDetailsResult = MutableLiveData<Response<CityDetails>>()
    val cityData: LiveData<Response<CityDetails>>
        get() = cityDetailsResult

    suspend fun getCityDetails(lat: String, lang: String, appId: String) {
        job.launch {
            try {
                cityDetailsResult.postValue(weatherRepositoryImp.fetchCityDetails(lat, lang, appId))
//                Log.d("ayush: ", result.body().toString())
            } catch (e: Exception) {
                Log.d("ayush e: ", e.toString())
            }
        }
    }
}