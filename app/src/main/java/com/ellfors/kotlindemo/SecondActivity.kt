package com.ellfors.kotlindemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.ellfors.kotlindemo.SecondAdapter.Companion.TYPE_ITEM
import com.ellfors.kotlindemo.base.BaseActivity
import com.ellfors.kotlindemo.base.recyclerview.BaseRecyclerAdapter.OnItemClickListener
import com.ellfors.kotlindemo.base.recyclerview.BaseRecyclerData
import com.ellfors.kotlindemo.http.model.response.FuliResponse
import com.ellfors.kotlindemo.presenter.contract.FuliContract
import com.ellfors.kotlindemo.presenter.impl.FuliPresenterImpl
import kotlinx.android.synthetic.main.activity_second.*
import javax.inject.Inject

/**
 * SecondActivity
 * 2018/7/3 15:36
 */
class SecondActivity : BaseActivity(), FuliContract.View, OnItemClickListener
{
    private var mAdapter: SecondAdapter? = null
    /**
     * List只可读，不可操作
     * MutableList继承于List，是可操作的集合
     */
    private var list: MutableList<BaseRecyclerData> = mutableListOf()

    @Inject
    lateinit var mPresenter: FuliPresenterImpl

    companion object
    {
        fun start(activity: Activity)
        {
            val intent = Intent(activity, SecondActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //注入
        getActivityComponent().inject(this)
        mPresenter.attachView(this)
        //初始化控件
        initView()
    }

    override fun onDestroy()
    {
        super.onDestroy()
        mPresenter.detachView()
    }

    private fun initView()
    {
        //初始化RecyclerView
        mAdapter = SecondAdapter(this, list)
        rcv_second.layoutManager = GridLayoutManager(this, 2)
        rcv_second.adapter = mAdapter
        mAdapter?.setOnItemClickListener(this@SecondActivity)
        //获取数据
        mPresenter.getList()
    }

    /**
     * RecyclerView Item点击事件
     */
    override fun onItemClick(view: View, position: Int, bean: BaseRecyclerData)
    {
        when (view.id)
        {
            R.id.iv_item_sencond -> showToast("第${position add 1}张图片")
        }
    }

    /**
     * 获取图片列表成功
     */
    override fun showList(data: List<FuliResponse>?)
    {
        list.addAll(data?.map { BaseRecyclerData(it, TYPE_ITEM) } as Iterable<BaseRecyclerData>)
        mAdapter?.setData(list)
    }

    /**
     * 获取图片列表失败
     */
    override fun showError(msg: String?)
    {
        showToast(msg)
    }

    override fun showFuli(beam: FuliResponse?)
    {
    }
}