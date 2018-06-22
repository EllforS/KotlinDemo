package com.ellfors.kotlindemo.presenter.contract

class FuliContract
{
    interface View
    {
        fun showFuli(img: String?)
        fun showError(errorMsg: String?)    //这个方法也可以放在父类中，抛出自定义的异常供业务层处理
    }

    interface Presenter
    {
        fun getFuli()
        fun attachView(view: View)
        fun detachView()
    }
}