package com.`in`.machinetask.utils

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    const val AUTH_TOKEN = "AuthToken"
    private var sharedPref: SharedPreferences? = null
    private const val PREF_NAME = "HRPreference"
    const val EMAIL = "email"
    const val PTMASTID = "PtmastId"
    const val USERNAME = "UserName"
    const val USERCODE = "UserCode"
    const val CompName = "CompName"
    const val COMPANY_NAME = "companyname"
    const val USER_NAME = "username"
    const val FIRST_NAME = "first_name"
    const val LAST_NAME = "last_name"
    const val HeadId = "headid"
    const val ADDRESS = "address"
    const val DEPT = "dept"
    const val EMPNAME = "empname"
    const val PROOFWITHIMAGE = "proofwithimage"
    const val AppUserName = "AppUserName"
    const val MOBILE = "mobile"
    const val DESIGNATION = "designation"
    const val CONTRACT_FILE = "contract_file"
    const val PROFILE_PHOTO = "PROFILE_PHOTO"
    const val HEADID = 0
    const val AuthPswd = "1269C48227AB2E13298623FEC51D88B0"
    const val DOB = "dob"
    const val GENDER = "gender"
    const val EMPID = "empId"
    const val PASSWORD = "password"
    const val CONFIRM_PASSWORD = "confirmPass"
    const val LOGINOTP = "loginotp"
    const val REGISTERED = "registered"
    const val CUMULATIVE_PATH = "cumulativePath"
    const val USERID = "UserID"
    const val DASH_LIST = "DashList"
    fun init(context: Context) {
        sharedPref = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun saveValue(key: String?, value: String?) {
        val editor = sharedPref!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveValue(key: String?, value: Long) {
        val editor = sharedPref!!.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun saveValue(key: String?, value: Int) {
        val editor = sharedPref!!.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun saveValue(key: String?, value: Boolean) {
        val editor = sharedPref!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun saveNoti(key: String?, value: Boolean) {
        val editor = sharedPref!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getString(key: String?): String? {
        return sharedPref!!.getString(key, "")
    }

    fun getInt(key: String?): Int {
        return sharedPref!!.getInt(key, 0)
    }

    fun getDays(key: String?): Int {
        return sharedPref!!.getInt(key, 90)
    }

    fun getLong(key: String?): Long {
        return sharedPref!!.getLong(key, 0L)
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPref!!.getBoolean(key, false)
    }

    fun getNoti(key: String?): Boolean {
        return sharedPref!!.getBoolean(key, true)
    }

    fun clearAll() {
        sharedPref!!.edit().clear().apply()
    }

    fun clear(key: String?) {}
}
