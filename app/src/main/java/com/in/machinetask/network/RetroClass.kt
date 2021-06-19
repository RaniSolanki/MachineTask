package com.`in`.machinetask.network

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.`in`.machinetask.ListResponse
import com.`in`.machinetask.MainActivity
import com.`in`.machinetask.R
import com.`in`.machinetask.utils.Preferences
import com.`in`.machinetask.utils.Util
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetroClass(private val context: Context) {
    private var mDialog: Dialog? = null
    var requestTag = 0
    var serviceCallBack: ServiceCallBack? = null
    var dialogBox: Dialog? = null
    var builder: AlertDialog.Builder? = null
    var throwable: Throwable? = null
    var layOK: RelativeLayout? = null
    private val callback: Callback<ArrayList<ListResponse>?> =
        object : Callback<ArrayList<ListResponse>?> {
            override fun onResponse(
                call: Call<ArrayList<ListResponse>?>,
                response: Response<ArrayList<ListResponse>?>
            ) {
                Util.showLog(response.toString()) //response.errorBody().source().readByteString()
                dismissLoading()

                    serviceCallBack!!.onSuccess(requestTag, response)

            }


            override fun onFailure(call: Call<ArrayList<ListResponse>?>, t: Throwable) {
                try {
                    builder = AlertDialog.Builder(context)
                    builder!!.setMessage(R.string.technical_error).setTitle(R.string.app_name)
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                            DialogInterface.OnClickListener { dialog, id ->
                                serviceCallBack!!.onFail(
                                    requestTag,
                                    t
                                )
                            })
                        .setNegativeButton("No",
                            DialogInterface.OnClickListener { dialog, id -> //  Action for 'NO' Button
                                dialog.cancel()
                            })
                    //Creating dialog box
                    val alert = builder!!.create()
                    //Setting the title manually
                    alert.setTitle(R.string.app_name)
                    alert.show()
                } catch (e: Exception) {
                }
            }
        }

    fun requestCallback(): Callback<*> {
        return callback
    }

    fun getRetrofit(isShowLoading: Boolean, pass: Boolean): IApi? {
        return if (Util.isOnline(context)) {
            System.setProperty("http.keepAlive", "false")
            val httpClient = OkHttpClient.Builder()
            httpClient.readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .retryOnConnectionFailure(false).addInterceptor { chain ->
                    val original = chain.request()
                    val builder = original.newBuilder()
                    //Request request = chain.request().newBuilder().addHeader("parameter", "value").build();
                    builder.header("Content-Type", "application/json")
                    builder.addHeader("Accept", "application/json")

                    val request =
                        builder.method(original.method(), original.body())
                            .build()
                    chain.proceed(request)
                }
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val client = httpClient.build()
            val retrofit = Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            if (isShowLoading && context is AppCompatActivity) showLoading()
            // prepare call in Retrofit 2.0
            //        Call<BaseResponce> call = api.callService(json);
            //asynchronous call
            //        call.enqueue(this);
            retrofit.create(IApi::class.java)
        } else {
           Toast.makeText(context,"No Internet connection Available",Toast.LENGTH_LONG).show()

            null
        }
    }

    private fun showLoading() {
        try {
            (context as AppCompatActivity).runOnUiThread {
                mDialog = null
                if (mDialog == null) {
                    mDialog = Dialog(context)
                    mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    mDialog!!.setContentView(R.layout.progress_dialog)
                    mDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    mDialog!!.setCanceledOnTouchOutside(false)
                    mDialog!!.setCancelable(false)
                    mDialog!!.show()
                }
            }
        } catch (e: Exception) {
            (context as AppCompatActivity).runOnUiThread {
                mDialog = null
                if (mDialog == null) {
                    mDialog = Dialog(context)
                    mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    mDialog!!.setContentView(R.layout.progress_dialog)
                    mDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    mDialog!!.setCanceledOnTouchOutside(false)
                    mDialog!!.setCancelable(false)
                    mDialog!!.show()
                }
            }
        }
    }

    fun dismissLoading() {
        try {
            (context as AppCompatActivity).runOnUiThread {
                if (mDialog != null) {
                    mDialog!!.cancel()
                    mDialog!!.dismiss()
                }
            }
        } catch (e: Exception) {
            (context as AppCompatActivity).runOnUiThread {
                if (mDialog != null) {
                    mDialog!!.cancel()
                    mDialog!!.dismiss()
                }
            }
            e.printStackTrace()
        }
    }

}
