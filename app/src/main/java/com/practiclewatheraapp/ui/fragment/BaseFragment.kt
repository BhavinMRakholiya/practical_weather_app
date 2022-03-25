package com.practiclewatheraapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.practiclewatheraapp.ui.activity.BaseActivity

abstract class BaseFragment<T : ViewDataBinding> : Fragment(){
    lateinit var mViewDataBinding: T
    private var mRootView: View? = null
    var baseActivity: BaseActivity<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModelObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, findContentView(), container, false)
        mRootView = mViewDataBinding.root

        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onReady()
    }

    abstract fun onReady()

    @LayoutRes
    abstract fun findContentView(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity = context as BaseActivity<*>?
            this.baseActivity = activity
        }

    }

    abstract fun setViewModelObservers()
}