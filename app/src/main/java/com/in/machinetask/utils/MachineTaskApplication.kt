package com.`in`.machinetask.utils

import android.app.Application

class MachineTaskApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Preferences.init(this)
        instance = this
    }

    override fun onTerminate() {
        // Executed when the program terminates
        super.onTerminate()
    }

    companion object {
        var instance: MachineTaskApplication? = null
    }
}
