package com.ellfors.kotlindemo.http.model

/**
 * 回调父类
 */
class BaseResponse<T>
{
    var error: Boolean = false
    var results: T? = null
}