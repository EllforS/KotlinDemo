package com.ellfors.kotlindemo.http.config

import com.ellfors.kotlindemo.http.model.BaseResponse
import com.ellfors.kotlindemo.http.model.response.FuliResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface HttpApi
{
    @GET("/api/data/福利/{limit}/{page}")
    fun getFuLi(
            @Path("limit") limit: Int,
            @Path("page") page: Int
    ): Flowable<BaseResponse<List<FuliResponse>>>
}