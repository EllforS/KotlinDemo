package com.ellfors.kotlindemo.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ellfors.kotlindemo.app.MyApp
import com.ellfors.kotlindemo.di.component.DaggerFragmentComponent
import com.ellfors.kotlindemo.di.component.FragmentComponent
import com.ellfors.kotlindemo.di.module.FragmentModule
import com.ellfors.kotlindemo.extension.ImageExt
import com.ellfors.kotlindemo.extension.ToastExt

/**
 * BaseFragment
 * 2018/6/22 12:00
 */
abstract class BaseFragment : Fragment(), ImageExt, ToastExt
{
    lateinit var mContext: Fragment

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        mContext = this
    }

    /**
     * 获取FragmentComponent
     */
    fun getFragmentComponent(): FragmentComponent
    {
        return DaggerFragmentComponent
                .builder()
                .appComponent(MyApp().getAppComponent())
                .fragmentModule(getFragmentModule())
                .build()
    }

    /**
     * 获取FragmentModule
     */
    private fun getFragmentModule(): FragmentModule
    {
        return FragmentModule(activity!!, this)
    }
}