package com.practiclewatheraapp.ui.fragment.help


import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentHelpBinding
import com.practiclewatheraapp.ui.fragment.BaseFragment


class HelpFragment : BaseFragment<FragmentHelpBinding>() {
    private var adapter: SliderPagerAdapter? = null

    override fun onReady() {
        adapter = SliderPagerAdapter(
            requireActivity()
        )
        mViewDataBinding.apply {
            this.pagerIntroSlider.adapter=adapter

        }
    }

    override fun findContentView() = R.layout.fragment_help

    override fun setViewModelObservers() {

    }
}