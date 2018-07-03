package com.ellfors.kotlindemo.presenter.contract

import com.ellfors.kotlindemo.base.BaseView
import com.ellfors.kotlindemo.http.model.response.FuliResponse

open class FuliContract
{
    interface View : BaseView
    {
        fun showFuli(beam: FuliResponse?)
        fun showList(data: List<FuliResponse>?)
    }

    interface Presenter
    {
        fun getFuli()
        fun getList()
    }
}