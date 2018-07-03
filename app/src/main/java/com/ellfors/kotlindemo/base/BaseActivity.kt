package com.ellfors.kotlindemo.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ellfors.kotlindemo.app.MyApp
import com.ellfors.kotlindemo.di.component.ActivityComponent
import com.ellfors.kotlindemo.di.component.DaggerActivityComponent
import com.ellfors.kotlindemo.di.module.ActivityModule
import com.ellfors.kotlindemo.extension.ImageExt
import com.ellfors.kotlindemo.extension.ValueExt
import com.ellfors.kotlindemo.extension.ToastExt

abstract class BaseActivity : AppCompatActivity(), ImageExt, ToastExt, ValueExt
{
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        mContext = this
    }

    /**
     * 获取ActivityComponent
     */
    fun getActivityComponent(): ActivityComponent
    {
        return DaggerActivityComponent
                .builder()
                .appComponent(MyApp().getAppComponent())
                .activityModule(getActivityModule())
                .build()
    }

    /**
     * 获取ActivityModule
     */
    private fun getActivityModule(): ActivityModule
    {
        return ActivityModule(this)
    }

}