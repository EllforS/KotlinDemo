package com.ellfors.kotlindemo.base.recyclerview

import android.os.Parcel
import android.os.Parcelable

/**
 * RecyclerView类型权重占比对象
 */
class BaseRecyclerTypeSpanCount
{
    var type: Int? = null       //类型
    var spanCount: Int? = null  //权重

    constructor(type: Int?, spanCount: Int?)
    {
        this.type = type
        this.spanCount = spanCount
    }
}