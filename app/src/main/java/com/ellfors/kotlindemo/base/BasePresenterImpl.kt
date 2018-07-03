package com.ellfors.kotlindemo.base

/**
 * BasePresenterImpl
 * 2018/7/3 15:39
 */
open class BasePresenterImpl<T : BaseView> : BasePresenter<T>
{
    var mView: T? = null

    override fun attachView(t: T)
    {
        this.mView = t
    }

    override fun detachView()
    {
        this.mView = null
    }

}