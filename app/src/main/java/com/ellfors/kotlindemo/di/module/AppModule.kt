package com.ellfors.kotlindemo.di.module

import com.ellfors.kotlindemo.app.MyApp
import com.ellfors.kotlindemo.http.HttpManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application需要注入的数据
 */
@Module
class AppModule constructor(var myApp: MyApp)
{
    @Provides
    @Singleton
    fun provideMyApp(): MyApp
    {
        return myApp
    }

    @Provides
    @Singleton
    fun provideHttpManager(): HttpManager
    {
        return HttpManager.getInstance()
    }

}