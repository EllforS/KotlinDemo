package com.ellfors.kotlindemo.base

import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscription

abstract class BaseSubscriber<T> : FlowableSubscriber<T>
{
    private var t: T? = null
    private var mSubscription: Subscription? = null

    /**
     * 这里简化一下方法，省的每次都写四个
     */
    abstract fun onSuccess(t: T?)

    abstract fun onFailed(e: Exception)

    override fun onSubscribe(s: Subscription)
    {
        mSubscription = s
        /*
            这里实际使用时可以判断是否有网络链接
            如果沒有直接走onError方法，不要接着request了
         */
        s.request(java.lang.Long.MAX_VALUE)
    }

    override fun onNext(t: T?)
    {
        this.t = t
        if (t != null)
            onSuccess(t)
    }

    override fun onError(t: Throwable)
    {
        onFailed(Exception(t.message))
        mSubscription?.cancel()
    }

    override fun onComplete()
    {
        if (t == null)
            onSuccess(null)
        mSubscription?.cancel()
    }
}
