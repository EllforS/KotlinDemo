package com.ellfors.kotlindemo.extension

import android.app.Activity
import android.app.Fragment
import android.widget.Toast

/**
 * 前台打印扩展
 */
interface ToastExt
{
    fun Activity.showToast(msg: String?)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun Activity.showToast(msg: Int)
    {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show()
    }

    fun Fragment.showToast(msg: String?)
    {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.showToast(msg: Int)
    {
        Toast.makeText(activity, msg.toString(), Toast.LENGTH_SHORT).show()
    }
}