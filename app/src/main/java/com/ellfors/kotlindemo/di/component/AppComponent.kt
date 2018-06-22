package com.ellfors.kotlindemo.di.component

import com.ellfors.kotlindemo.app.MyApp
import com.ellfors.kotlindemo.di.module.AppModule
import com.ellfors.kotlindemo.http.HttpManager
import dagger.Component
import javax.inject.Singleton

/**
 * Application注入器
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent
{
    fun myApp(): MyApp
    fun httpManager(): HttpManager

    fun inject(myApp: MyApp)
}
