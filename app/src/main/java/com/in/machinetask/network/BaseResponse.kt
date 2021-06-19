package com.`in`.machinetask.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("ResponseCode")
    @Expose
    var responseCode: Int? = null

    @SerializedName("ResponseStatus")
    @Expose
    private var responseStatus: Boolean? = null

    @SerializedName("ResponseMessage")
    @Expose
    var responseMessage: String? = null

    @SerializedName("ResponsePacket")
    @Expose
    var responsePacket: T? = null
        private set

    fun withResponseCode(responseCode: Int?): BaseResponse<T> {
        this.responseCode = responseCode
        return this
    }

    fun setResponsePacket(responsePacket: T) {
        this.responsePacket = responsePacket
    }

    fun isResponseStatus(): Boolean {
        return responseStatus!!
    }

    fun setResponseStatus(responseStatus: Boolean) {
        this.responseStatus = responseStatus
    }

    fun withResponseStatus(responseStatus: Boolean?): BaseResponse<T> {
        this.responseStatus = responseStatus
        return this
    }

    fun withResponseMessage(responseMessage: String?): BaseResponse<T> {
        this.responseMessage = responseMessage
        return this
    }

    fun withResponsePacket(responsePacket: T): BaseResponse<T> {
        this.responsePacket = responsePacket
        return this
    }
}

