package com.practiclewatheraapp.ui.fragment

import androidx.navigation.fragment.findNavController
import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override fun onReady() {

        mViewDataBinding.addFab.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMapFragment())
        }
    }

    override fun findContentView() = R.layout.fragment_home

    override fun setViewModelObservers() {

    }
}