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
            is Activity -> if (!msg.isNullOrBlank()) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            is Fragment -> if (!msg.isNullOrBlank()) Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
            else -> if (!msg.isNullOrBlank()) Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }
}