package com.ellfors.kotlindemo.di.component

import android.app.Activity
import com.ellfors.kotlindemo.MainActivity
import com.ellfors.kotlindemo.di.ActivityScope
import com.ellfors.kotlindemo.di.module.ActivityModule
import dagger.Component

/**
 * Activity注入器
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent
{
    fun activity(): Activity

    fun inject(activity: MainActivity)
}
