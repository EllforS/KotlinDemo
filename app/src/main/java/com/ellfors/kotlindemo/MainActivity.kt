package com.ellfors.kotlindemo

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.ellfors.kotlindemo.base.BaseActivity
import com.ellfors.kotlindemo.http.model.response.FuliResponse
import com.ellfors.kotlindemo.presenter.contract.FuliContract
import com.ellfors.kotlindemo.presenter.impl.FuliPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), FuliContract.View
{
    @Inject
    lateinit var mainFragment: MainFragment
    @Inject
    lateinit var fuliPresenter: FuliPresenterImpl

    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
    }

    override fun onDestroy()
    {
        super.onDestroy()
        fuliPresenter.detachView()
    }

    private fun initData()
    {
        //注入
        getActivityComponent().inject(this@MainActivity)
        fuliPresenter.attachView(this@MainActivity)
        //显示图片
        iv_local.displayImg(R.drawable.timg)
        iv_local.setOnClickListener({ SecondActivity.start(this@MainActivity) })
        //获取网络图片
        fuliPresenter.getFuli()
        //装载Fragment
        initFragment()
    }

    private fun initFragment()
    {
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.fl_fragment, mainFragment)
        ft.commit()
    }

    /**
     * 获取网络图片成功
     */
    override fun showFuli(bean: FuliResponse?)
    {
        imageUrl = bean?.url
        //显示图片
        iv_net.displayImg(imageUrl)
    }

    /**
     * 获取网络图片失败
     */
    override fun showError(errorMsg: String?)
    {
        showToast(errorMsg)
    }

    override fun showList(data: List<FuliResponse>?)
    {
    }
}