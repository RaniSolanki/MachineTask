package com.`in`.machinetask.network

import android.content.Context
import com.`in`.machinetask.ListResponse
import com.`in`.machinetask.utils.BaseView
import java.util.*
import kotlin.collections.ArrayList

interface ListContract {
    interface View : BaseView<Navigator> {
        fun listResponse(listResponses: ArrayList<ListResponse>)
    }

    interface Navigator {
        fun getListResponse(page: Int, context: Context)
    }
}