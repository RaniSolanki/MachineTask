package com.`in`.machinetask

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ListResponse {

    private var id: String? = null

    private var author: String? = null

    private var width: Int? = null

    private var height: Int? = null

    private var url: String? = null

    private var download_url: String? = null

    constructor(
        id: String?,
        author: String?,
        width: Int?,
        height: Int?,
        url: String?,
        download_url: String?
    ) {
        this.id = id
        this.author = author
        this.width = width
        this.height = height
        this.url = url
        this.download_url = download_url
    }


    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getAuthor(): String? {
        return author
    }

    fun setAuthor(author: String?) {
        this.author = author
    }

    fun getWidth(): Int? {
        return width
    }

    fun setWidth(width: Int?) {
        this.width = width
    }

    fun getHeight(): Int? {
        return height
    }

    fun setHeight(height: Int?) {
        this.height = height
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getDownloadUrl(): String? {
        return download_url
    }

    fun setDownloadUrl(downloadUrl: String?) {
        this.download_url = downloadUrl
    }
}