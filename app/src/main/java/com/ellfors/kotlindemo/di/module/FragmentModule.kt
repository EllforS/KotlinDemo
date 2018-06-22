package com.ellfors.kotlindemo.di.module

import android.app.Activity
import android.support.v4.app.Fragment
import com.ellfors.kotlindemo.di.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Fragment需要注入的数据
 */
@Module
class FragmentModule constructor(var activity: Activity, var fragment: Fragment)
{
    @Provides
    @FragmentScope
    fun provideActivity(): Activity
    {
        return activity
    }

    @Provides
    @FragmentScope
    fun provideFragment(): Fragment
    {
        return fragment
    }

    @Provides
    @FragmentScope
    fun getTestString(): String
    {
        return "这里是注入的字符串"
    }

}