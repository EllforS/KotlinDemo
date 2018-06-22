package com.ellfors.kotlindemo.http.utils

import com.ellfors.kotlindemo.http.model.BaseResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxUtils
{
    companion object
    {
        /**
         * 统一线程处理,网络线程生成数据，主线程消费
         */
        fun <T> rxSchedulerHelper(): FlowableTransformer<T, T>
        {
            return FlowableTransformer {
                it.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }

        /**
         * 统一返回结果处理
         */
        fun <T> handleResult(): FlowableTransformer<BaseResponse<T>, T>
        {
            return FlowableTransformer {
                it.flatMap {
                    if (!it.error)
                        createData(it.results)
                    else
                        Flowable.error(Exception("这里可以写个自定义的Exception，抛出不同的异常，在BaseSubscriber中可以统一处理"))
                }
            }
        }

        /**
         * 生成观察者
         */
        private fun <T> createData(t: T): Flowable<T>
        {
            return Flowable.create(
                    {
                        try
                        {
                            if (t != null)
                                it.onNext(t)
                            it.onComplete()
                        }
                        catch (e: Exception)
                        {
                            it.onError(e)
                        }
                    }, BackpressureStrategy.BUFFER)
        }
    }
}