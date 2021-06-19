package com.`in`.machinetask.network

import com.`in`.machinetask.ListResponse
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import kotlin.collections.ArrayList

class UserRepositories : DataSource {


    override fun getListService(
        page: Int,
        registerPresenter: ServiceCallBack?,
        retroClass: RetroClass
    ) {
        try {
            val responseCall: Call<ArrayList<ListResponse>> =
                retroClass.getRetrofit(true, true)!!.callGetListService(page)
            retroClass.serviceCallBack = registerPresenter
            retroClass.requestTag = IApi.COMMON_TAG1
            responseCall.enqueue(retroClass.requestCallback() as Callback<ArrayList<ListResponse>>)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    companion object {
        private var INSTANCE: UserRepositories? = null
        val instance: UserRepositories?
            get() {
                if (INSTANCE == null) {
                    INSTANCE = UserRepositories()
                }
                return INSTANCE
            }
    }



}
