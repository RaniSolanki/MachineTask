package com.`in`.machinetask.network

import org.json.JSONObject

interface DataSource {

    fun getListService(
        page: Int,
        registerPresenter: ServiceCallBack?,
        retroClass: RetroClass
    )


}

