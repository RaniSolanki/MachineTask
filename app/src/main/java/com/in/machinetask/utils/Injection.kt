package com.`in`.machinetask.utils

import android.content.Context
import com.`in`.machinetask.network.UserRepositories


object Injection {
    fun provideLoginRepository(context: Context?): UserRepositories? {
        return UserRepositories.instance
    }
}


