package com.ellfors.kotlindemo.base.recyclerview

/**
 * BaseRecyclerData
 * 2018/3/26 15:02
 */
class BaseRecyclerData
{
    var data: Any? = null
    var type: Int = 0

    constructor(data: Any)
    {
        this.data = data
        this.type = 0
    }

    constructor(data: Any, type: Int)
    {
        this.data = data
        this.type = type
    }
}
