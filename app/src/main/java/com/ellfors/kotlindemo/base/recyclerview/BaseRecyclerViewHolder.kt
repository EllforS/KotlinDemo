package com.ellfors.kotlindemo.base.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import com.ellfors.kotlindemo.extension.ImageExt

/**
 * RecyclerView类型对象基类
 */
open class BaseRecyclerViewHolder(itemView: View, private val adapter: BaseRecyclerAdapter) : RecyclerView.ViewHolder(itemView), ImageExt
{
    init
    {
        ButterKnife.bind(this, itemView)
    }

    /**
     * 获取真实的指针位置
     */
    private val realPosition: Int
        get()
        {
            return if (adapterPosition == RecyclerView.NO_POSITION)
                position
            else
                adapterPosition
        }

    /**
     * 设置公用的点击事件
     */
    fun setOnClickListener(view: View?)
    {
        view?.setOnClickListener {
            if (realPosition != RecyclerView.NO_POSITION && adapter.items != null && adapter.items!!.size > 0 && adapter.mListener != null)
                adapter.mListener!!.onItemClick(it, realPosition, adapter.getData(realPosition)!!)
        }
    }

    /**
     * 设置公用的长按事件
     */
    fun setOnLongClickListener(view: View?)
    {
        view?.setOnLongClickListener {
            if (realPosition != RecyclerView.NO_POSITION && adapter.items != null && adapter.items!!.size > 0 && adapter.mLongListener != null)
                adapter.mLongListener!!.onItemLongClick(it, realPosition, adapter.getData(realPosition)!!)
            true
        }
    }
}