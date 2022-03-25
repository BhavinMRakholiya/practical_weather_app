package com.practiclewatheraapp.ui.fragment.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentHomeBinding
import com.practiclewatheraapp.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val mHomeViewModel by viewModels<HomeViewModel>()
    private val mCityAdapter: CityAdapter by lazy {
        CityAdapter { cityData, isLongClicked ->
            if (!isLongClicked) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToCityDetailsFragment(
                        cityData.lat.toString(),
                        cityData.longitude.toString()
                    )
                )
            } else {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(getString(R.string.lbl_sure_to_remove))
                    .setPositiveButton(
                        R.string.lbl_yes
                    ) { dialogInterface, i ->
                        lifecycleScope.launch {
                            mHomeViewModel.deleteCityData(cityData)
                        }
                    }
                    .setNegativeButton(
                        R.string.lbl_no
                    ) { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                    .show()
            }
        }
    }

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
        with(mHomeViewModel) {
            cityData.observe(requireActivity(), Observer {
                if (it.isNotEmpty()) {
                    mViewDataBinding.apply {
                        this.rvCity.isVisible = true
                        this.tvNoData.isVisible = false
                    }
                    mCityAdapter.updateData(it)
                } else {
                    mViewDataBinding.apply {
                        this.rvCity.isVisible = false
                        this.tvNoData.isVisible = true
                    }
                }
            })
            deleteCity.observe(requireActivity(), Observer {
                lifecycleScope.launch {
                    mHomeViewModel.getCityData()
                }
            })
        }
    }
}