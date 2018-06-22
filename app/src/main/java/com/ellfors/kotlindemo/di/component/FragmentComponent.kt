package com.ellfors.kotlindemo.di.component

import android.app.Activity
import android.support.v4.app.Fragment
import com.ellfors.kotlindemo.MainFragment
import com.ellfors.kotlindemo.di.FragmentScope
import com.ellfors.kotlindemo.di.module.FragmentModule
import dagger.Component

/**
 * Fragment注入器
 */
@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(FragmentModule::class)])
interface FragmentComponent
{
    fun activity(): Activity
    fun fragment(): Fragment

    fun inject(mainFragment: MainFragment)
}