package com.ellfors.kotlindemo.di.module

import android.app.Activity
import com.ellfors.kotlindemo.MainFragment
import com.ellfors.kotlindemo.di.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Activity需要注入的数据
 */
@Module
class ActivityModule constructor(var activity: Activity)
{
    @Provides
    @ActivityScope
    fun provideActivity(): Activity
    {
        return activity
    }

    @Provides
    @ActivityScope
    fun provcideMainFragment(): MainFragment
    {
        return MainFragment()
    }

}