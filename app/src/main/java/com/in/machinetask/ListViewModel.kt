package com.`in`.machinetask

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.`in`.machinetask.network.ListContract
import com.`in`.machinetask.network.ListNavigator
import com.`in`.machinetask.utils.Injection
import org.json.JSONException
import org.json.JSONObject

class ListViewModel : ViewModel, ListContract.View {
    private lateinit var navigator: ListContract.Navigator
    var context: Context? = null

     var id: String? = null

     var author: String? = null

     var width: Int? = null

     var height: Int? = null

     var url: String? = null

     var download_url: String? = null


    var complaintActionViewModelArrayList: ArrayList<ListViewModel> =
        ArrayList<ListViewModel>()
     var complaintActionResponseMutableLiveData: MutableLiveData<ArrayList<ListResponse>> =
        MutableLiveData<ArrayList<ListResponse>>()

     var arrayListMutableLiveData: MutableLiveData<ArrayList<ListViewModel>> =
        MutableLiveData<ArrayList<ListViewModel>>()




    constructor(context: Context?) {
        this.context = context
        Injection.provideLoginRepository(context)?.let { ListNavigator(it, this) }
    }

    constructor(listResponse: ListResponse) {
        id = listResponse.getId()
        author = listResponse.getAuthor()
        width = listResponse.getWidth()
        height = listResponse.getHeight()
        url = listResponse.getUrl()
        download_url = listResponse.getDownloadUrl()

    }


    fun getList(
        page: Int,
        context: Context
    ) {
        navigator.getListResponse(page, context)
    }


    override fun listResponse(listResponses: ArrayList<ListResponse>) {
        if ( listResponses.size != 0) {
            var listViewModel: ListViewModel?
            for (i in 0 until listResponses.size) {

                val listResponse =
                    ListResponse( listResponses.get(i).getId(),
                        listResponses.get(i).getAuthor(),
                        listResponses.get(i).getWidth(),
                        listResponses.get(i).getHeight(),
                        listResponses.get(i).getUrl(),
                        listResponses.get(i).getDownloadUrl())
                listViewModel = ListViewModel(listResponse )
                complaintActionViewModelArrayList.add(listViewModel)

            }
            arrayListMutableLiveData.setValue(complaintActionViewModelArrayList)
        }
        complaintActionResponseMutableLiveData.setValue(listResponses)
    }




    override fun setNavigator(navigator: ListContract.Navigator) {
        this.navigator=navigator
    }
}
