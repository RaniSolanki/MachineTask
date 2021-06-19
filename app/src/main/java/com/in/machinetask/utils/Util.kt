package com.`in`.machinetask.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.`in`.machinetask.R

object Util {
    fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
            isLoadingVisible = false
        }
    }

    private var isLoadingVisible = false
    private var mProgressDialog: ProgressDialog? = null
    fun showProgress(mContext: Context?) {
        if (!isLoadingVisible) {
            isLoadingVisible = true
            if (mContext == null) {
                return
            }
            mProgressDialog = ProgressDialog(mContext)
            mProgressDialog!!.setTitle(mContext.getString(R.string.app_name))
            mProgressDialog!!.setMessage("Loading...")
            mProgressDialog!!.isIndeterminate = false
            mProgressDialog!!.setCanceledOnTouchOutside(false)
            mProgressDialog!!.setCancelable(false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val drawable = ProgressBar(mContext).indeterminateDrawable.mutate()
                drawable.setColorFilter(
                    ContextCompat.getColor(mContext, R.color.black),
                    PorterDuff.Mode.SRC_IN
                )
                mProgressDialog!!.setIndeterminateDrawable(drawable)
            }
            try {
                mProgressDialog!!.show()
            } catch (e: Exception) {
            }
        }
    }

    private const val IS_DEBUG = true
    fun showLog(logMessage: String?) {
        if (IS_DEBUG) {
            try {
                Log.d("GRABGEINE LOG :", logMessage!!)
            } catch (e: Exception) {
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun isOnline(context: Context): Boolean {
        val conMgr =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo!!
            .isAvailable && conMgr.activeNetworkInfo!!.isConnected
    }

    fun showAlertBox(
        context: Context,
        msg: String?,
        okListener: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(context)
            .setTitle(context.resources.getString(R.string.app_name)).setMessage(msg)
            .setPositiveButton("OK", okListener).show().setCancelable(false)
    }
}
