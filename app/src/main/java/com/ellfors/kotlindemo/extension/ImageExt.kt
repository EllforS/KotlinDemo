package com.ellfors.kotlindemo.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * 图片扩展
 */
interface ImageExt
{
    /**
     * 加载图片
     */
    fun ImageView.displayImg(url: Any?)
    {
        when (url)
        {
            is String ->
            {
                Glide.with(context)
                        .load(url)
                        .apply(RequestOptions().centerCrop())
                        .into(this)
            }
            is Int ->
            {
                Glide.with(context)
                        .load(url)
                        .apply(RequestOptions().centerCrop())
                        .into(this)
            }
            else -> setImageResource(0)
        }
    }
}