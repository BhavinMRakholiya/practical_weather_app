package com.practiclewatheraapp.ui.fragment.map

import androidx.lifecycle.ViewModel
import com.practiclewatheraapp.data.repository.WeatherRepositoryImp
import com.practiclewatheraapp.source.entity.CityData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val weatherRepositoryImp: WeatherRepositoryImp):ViewModel() {

    suspend fun saveCityData(cityData: CityData) {
        weatherRepositoryImp.saveCityData(cityData)
    }
}