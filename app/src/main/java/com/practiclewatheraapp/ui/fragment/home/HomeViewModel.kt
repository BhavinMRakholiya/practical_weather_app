package com.practiclewatheraapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practiclewatheraapp.data.repository.WeatherRepositoryImp
import com.practiclewatheraapp.source.entity.CityData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepositoryImp: WeatherRepositoryImp) :
    ViewModel() {
    private val cityResult = MutableLiveData<List<CityData>>()
    val deleteCity = MutableLiveData<Int>()

    val cityData: LiveData<List<CityData>>
        get() = cityResult

    suspend fun getCityData() {
        cityResult.postValue(weatherRepositoryImp.getCityData())
    }


    suspend fun deleteCityData(cityData: CityData) {
        deleteCity.postValue(weatherRepositoryImp.deleteCityData(cityData))
    }
}