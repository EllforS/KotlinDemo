package com.ellfors.kotlindemo.base

/**
 * BasePresenter
 * 2018/7/3 16:05
 */
interface BasePresenter<T : BaseView>
{
    fun attachView(t: T)
    fun detachView()
}