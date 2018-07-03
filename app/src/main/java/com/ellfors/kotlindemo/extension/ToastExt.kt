package com.ellfors.kotlindemo.extension

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * 前台打印扩展
 */
interface ToastExt
{
    fun Context.showToast(msg: String?)
    {
        when (this)
        {
            is Activity -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            is Fragment -> Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }
}