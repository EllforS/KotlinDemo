package com.ellfors.kotlindemo.app

import android.app.Application
import com.ellfors.kotlindemo.di.component.AppComponent
import com.ellfors.kotlindemo.di.component.DaggerAppComponent
import com.ellfors.kotlindemo.di.module.AppModule

class MyApp : Application()
{
    override fun onCreate()
    {
        super.onCreate()
    }

    /**
     * 获取AppComponent
     */
    fun getAppComponent(): AppComponent
    {
        return DaggerAppComponent
                .builder()
                .appModule(getAppModule())
                .build()
    }

    /**
     * 获取AppModule
     */
    fun getAppModule(): AppModule
    {
        return AppModule(this)
    }
}