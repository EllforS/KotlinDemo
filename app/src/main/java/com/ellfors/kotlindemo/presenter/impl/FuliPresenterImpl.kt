package com.ellfors.kotlindemo.presenter.impl

import com.ellfors.kotlindemo.base.BasePresenterImpl
import com.ellfors.kotlindemo.base.BaseSubscriber
import com.ellfors.kotlindemo.http.HttpManager
import com.ellfors.kotlindemo.http.model.response.FuliResponse
import com.ellfors.kotlindemo.http.utils.RxUtils
import com.ellfors.kotlindemo.presenter.contract.FuliContract
import javax.inject.Inject

/**
 * 获取网络图片逻辑类
 */
class FuliPresenterImpl @Inject constructor(private val manager: HttpManager) : BasePresenterImpl<FuliContract.View>(), FuliContract.Presenter
{
    /* 弄俩随机数  省的每次都一样  蛋疼 */
    private val limit: Int = (Math.random() * 20 + 1).toInt()
    private val page: Int = (Math.random() * 10 + 1).toInt()

    /**
     * 获取网络图片
     */
    override fun getFuli()
    {
        manager.getGsonHttpApi()
                .getFuLi(limit, page)
                .compose(RxUtils.handleResult())
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(object : BaseSubscriber<List<FuliResponse>>()
                           {
                               override fun onSuccess(t: List<FuliResponse>?)
                               {
                                   mView?.showFuli(t?.get(0))
                               }

                               override fun onFailed(e: Exception)
                               {
                                   mView?.showError(e.message)
                               }
                           })
    }

    override fun getList()
    {
        manager.getGsonHttpApi()
                .getFuLi(20, 10)
                .compose(RxUtils.handleResult())
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(object : BaseSubscriber<List<FuliResponse>>()
                           {
                               override fun onSuccess(t: List<FuliResponse>?)
                               {
                                   mView?.showList(t)
                               }

                               override fun onFailed(e: Exception)
                               {
                                   mView?.showError(e.message)
                               }
                           })
    }
}