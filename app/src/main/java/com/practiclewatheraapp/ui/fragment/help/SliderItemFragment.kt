package com.practiclewatheraapp.ui.fragment.help


import android.os.Bundle
import androidx.annotation.StringRes
import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentSliderItemBinding
import com.practiclewatheraapp.ui.fragment.BaseFragment


class SliderItemFragment : BaseFragment<FragmentSliderItemBinding>() {
    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            position = arguments?.getInt(ARG_POSITION)!!
        }
    }


    companion object {
        private const val ARG_POSITION = "slider-position"

        @StringRes
        private val PAGE_TITLES =
            intArrayOf(R.string.home, R.string.bookmark, R.string.details, R.string.remove_data)

        @StringRes
        private val PAGE_TEXT = intArrayOf(
            R.string.home_text, R.string.shop_text, R.string.offers_text, R.string.rewards_text
        )

        @StringRes
        private val PAGE_IMAGE = intArrayOf(
            R.drawable.ic_step1, R.drawable.ic_step2, R.drawable.ic_step3, R.drawable.ic_step4
        )


        fun newInstance(position: Int): SliderItemFragment {
            val fragment = SliderItemFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onReady() {
        mViewDataBinding.apply {
            this.tvTitle.setText(PAGE_TITLES[position])
            this.tvDesc.setText(PAGE_TEXT[position])
            this.imageView.setImageResource(PAGE_IMAGE[position])
        }
    }

    override fun findContentView() = R.layout.fragment_slider_item

    override fun setViewModelObservers() {

    }
}