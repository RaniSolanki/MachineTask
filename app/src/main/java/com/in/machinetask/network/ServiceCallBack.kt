package com.`in`.machinetask.network

import com.`in`.machinetask.ListResponse
import retrofit2.Response

interface ServiceCallBack {
    fun onSuccess(tag: Int, baseResponse: Response<ArrayList<ListResponse>?>)
    fun onFail(requestTag: Int, t: Throwable?)
}
