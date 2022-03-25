package com.practiclewatheraapp.ui.fragment.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentHomeBinding
import com.practiclewatheraapp.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val mHomeViewModel by viewModels<HomeViewModel>()
    private val mCityAdapter: CityAdapter by lazy { CityAdapter {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCityDetailsFragment(it.lat.toString(),it.longitude.toString()))
    } }

    override fun onReady() {
        mViewDataBinding.rvCity.adapter = mCityAdapter
        mViewDataBinding.addFab.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMapFragment())
        }

        lifecycleScope.launch {
            mHomeViewModel.getCityData()
        }
    }

    override fun findContentView() = R.layout.fragment_home

    override fun setViewModelObservers() {
        with(mHomeViewModel){
            cityData.observe(requireActivity(), Observer {
                mCityAdapter.updateData(it)
            })
        }
    }
}