package com.`in`.machinetask.network

import android.content.Context
import com.`in`.machinetask.ListResponse
import retrofit2.Response
import java.util.*

class ListNavigator(
    protected val listDataSource: DataSource,
    private val mListView: ListContract.View
) :
    ListContract.Navigator, ServiceCallBack {
    private var context: Context? = null
    override fun onFail(tag: Int, t: Throwable?) {}


    override fun getListResponse(page: Int, context: Context) {
        val retroClass = RetroClass(context)
        this.context = context
        listDataSource.getListService(page, this, retroClass)
    }

    override fun onSuccess(
        tag: Int,
        baseResponse: Response<ArrayList<ListResponse>?>
    ) {
        if (tag == IApi.COMMON_TAG1) {
            val response: ArrayList<ListResponse>? = baseResponse!!.body()
            if (response != null) {
                mListView.listResponse(response)
            }
        }
    }

    init {
        mListView.setNavigator(this)
    }


}
