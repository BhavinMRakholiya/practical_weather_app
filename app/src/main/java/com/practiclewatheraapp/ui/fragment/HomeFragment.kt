package com.practiclewatheraapp.ui.fragment

import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override fun onReady() {
        mViewDataBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {

                }
                R.id.action_help -> {
                }
            }
            true
        }
    }

    override fun findContentView() = R.layout.fragment_home

    override fun setViewModelObservers() {

    }
}