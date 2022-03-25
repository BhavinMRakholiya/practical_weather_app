package com.practiclewatheraapp.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(){
    protected lateinit var mActivity: AppCompatActivity
    private lateinit var mViewDataBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        mViewDataBinding = DataBindingUtil.setContentView(this, findContentView())

        onReady(savedInstanceState)
    }

    abstract fun onReady(savedInstanceState: Bundle?)

    @LayoutRes
    abstract fun findContentView(): Int

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }
}