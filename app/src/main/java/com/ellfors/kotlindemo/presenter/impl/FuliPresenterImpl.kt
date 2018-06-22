package com.ellfors.kotlindemo.presenter.impl

import com.ellfors.kotlindemo.base.BaseSubscriber
import com.ellfors.kotlindemo.http.HttpManager
import com.ellfors.kotlindemo.http.model.response.FuliResponse
import com.ellfors.kotlindemo.http.utils.RxUtils
import com.ellfors.kotlindemo.presenter.contract.FuliContract
import javax.inject.Inject

/**
 * 获取网络图片逻辑类
 */
class FuliPresenterImpl @Inject constructor(private val manager: HttpManager) : FuliContract.Presenter
{
    var mView: FuliContract.View? = null

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
                                   mView?.showFuli(t?.get(0)?.url)
                               }

                               override fun onFailed(e: Exception)
                               {
                                   mView?.showError(e.message)
                               }
                           })
    }

    /**
     * 绑定View，正常要写在父类中，懒得动弹就放这了
     */
    override fun attachView(view: FuliContract.View)
    {
        mView = view
    }

    /**
     * 解绑
     */
    override fun detachView()
    {
        mView = null
    }

}