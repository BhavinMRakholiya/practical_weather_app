package com.practiclewatheraapp.ui.fragment.city

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentCityDetailsBinding
import com.practiclewatheraapp.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CityDetailsFragment : BaseFragment<FragmentCityDetailsBinding>() {
    private val mCityViewModel by viewModels<CityViewModel>()
    private val mNavArgs: CityDetailsFragmentArgs by navArgs()

    override fun onReady() {

        lifecycleScope.launch {
            mCityViewModel.getCityDetails(
                mNavArgs.lat,
                mNavArgs.lang,
                "54abe9210a33d5d79f2055af67c4e4a5"
            )
        }
    }

    override fun findContentView() = R.layout.fragment_city_details

    override fun setViewModelObservers() {
        with(mCityViewModel) {
            cityData.observe(requireActivity(), Observer {
                if (it != null) {
                    mViewDataBinding.apply {
                        if (it.body()!=null){
                            this.tvTemp.text= it.body()?.main?.temp
                            this.tvHumidity.text= it.body()?.main?.humidity
                            this.tvWind.text= it.body()?.wind?.speed
                            this.tvRainChance.text= it.body()?.weather?.get(0)?.main
                        }
                    }
                }
            })
        }
    }
}