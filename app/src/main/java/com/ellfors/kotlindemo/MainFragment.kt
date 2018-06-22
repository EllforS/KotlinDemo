package com.ellfors.kotlindemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ellfors.kotlindemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment()
{
    @Inject
    lateinit var str: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init()
    {
        //注入
        getFragmentComponent().inject(this@MainFragment)
        //显示文字
        tv_fragment.text = str
    }

}