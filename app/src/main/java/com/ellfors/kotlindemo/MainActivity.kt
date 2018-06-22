package com.ellfors.kotlindemo

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.ellfors.kotlindemo.base.BaseActivity
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
        //Kotlin的kotlin-android-extensions框架可以直接找到相关id的view
        //不需要绑定了，很好用，就是写onclick方法的时候比较蛋疼，没有ButterKnife那种统一管理的方法
        //我还在找解决方法
        iv_local.displayImg(R.drawable.timg)
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
    override fun showFuli(img: String?)
    {
        imageUrl = img
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
}