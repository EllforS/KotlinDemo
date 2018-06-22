package com.ellfors.kotlindemo.http

import android.util.Log
import com.ellfors.kotlindemo.http.config.HttpApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpManager
{
    companion object
    {
        private const val TAG = "HttpManager"
        const val BASE_URL = "http://gank.io"
        const val DEFAULT_TIME = 10
        lateinit var loggingInterceptor: HttpLoggingInterceptor

        @Volatile
        private var instance: HttpManager? = null

        /**
         * 单例构造
         */
        fun getInstance(): HttpManager
        {
            if (instance == null)
            {
                synchronized(HttpManager::class.java)
                {
                    if (instance == null)
                    {
                        instance = HttpManager()
                    }
                }
            }
            return instance!!
        }
    }

    //注意！这个方法是kotlin自带的，初始化时就会调用，貌似跟java的无参构造差不多
    init
    {
        loggingInterceptor = HttpLoggingInterceptor { Log.d(TAG, it) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * 获取Gson解析的HttpApi
     */
    fun getGsonHttpApi(): HttpApi
    {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIME.toLong(), java.util.concurrent.TimeUnit.SECONDS)
        builder.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(HttpApi::class.java)
    }
}