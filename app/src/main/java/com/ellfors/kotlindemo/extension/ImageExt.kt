package com.ellfors.kotlindemo.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * 图片扩展
 *
 * kotlin可以直接扩展官方控件的方法，不管Activity、view、哪怕Int和String都行
 * 而且注意这里是接口，可以直接写默认实现，很方便，需要的地方直接实现这个接口就可以了
 */
interface ImageExt
{
    /**
     * 加载网络图片
     */
    fun ImageView.displayImg(url: String?)
    {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions().centerCrop())
                .into(this)
    }

    /**
     * 加载本地图片
     */
    fun ImageView.displayImg(path: Int)
    {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions().centerCrop())
                .into(this)
    }
}