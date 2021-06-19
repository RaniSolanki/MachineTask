package com.`in`.machinetask.network

import com.`in`.machinetask.ListResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface IApi {


    @GET("list")
    fun callGetListService(@Query("page") page: Int): Call<ArrayList<ListResponse>>

    companion object {
        const val BASE_URL = "https://picsum.photos/v2/"
        const val COMMON_TAG1 = 1001

    }
}
