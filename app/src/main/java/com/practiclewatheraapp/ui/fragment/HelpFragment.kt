package com.practiclewatheraapp.ui.fragment

import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentHelpBinding
import com.practiclewatheraapp.databinding.FragmentHomeBinding

class HelpFragment : BaseFragment<FragmentHelpBinding>() {


    override fun onReady() {
//        mViewDataBinding.bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.action_home -> {
//
//                }
//                R.id.action_help -> {
//                }
//            }
//            true
//        }
    }

    override fun findContentView() = R.layout.fragment_help

    override fun setViewModelObservers() {

    }
}