package com.ellfors.kotlindemo.extension

/**
 * 整形、长整型等扩展
 * 2018/7/3 17:46
 */
interface ValueExt
{
    /**
     * 加
     *
     * 中缀方法 将传统的 X.add(Y) 替换为 X add Y
     */
    infix fun Int.add(value: Int): Int
    {
        return this + value
    }

    /**
     * 减
     */
    infix fun Int.reduce(value: Int): Int
    {
        return this - value
    }

    /**
     * 乘
     */
    infix fun Int.multi(value: Int): Long
    {
        return (this * value).toLong()
    }

    /**
     * 除
     */
    infix fun Int.divide(value: Int): Double
    {
        return (this / value).toDouble()
    }
}