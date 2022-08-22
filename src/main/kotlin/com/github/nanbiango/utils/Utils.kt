package com.github.nanbiango.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.ui.Messages
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.time.Duration

/**
 * 扩展字符串消息
 */
object Utils {

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .readTimeout(Duration.ofSeconds(10))
        .callTimeout(Duration.ofSeconds(10))
        .build()

    public val gson = GsonBuilder().setPrettyPrinting().create()

    //普通消息
    fun showMessage(message: String) = Messages.showInfoMessage(message, "提示")

    //错误消息
    fun showErrorMessage(message: String) = Messages.showErrorDialog(message, "异常")

    /**
     * 发送httpPost请求
     */
    fun httpPost(url: String, body: String): String? {
        val request = Request.Builder()
            .url(url)
            .post(body.toRequestBody("application/json".toMediaType()))
            .build()
        val respCall = okHttpClient.newCall(request).execute()

        if (respCall.code == 200) {
            return respCall.body?.string()
        }
        return null
    }

    fun httpFromPost(url: String, body: String): String? {
        val request = Request.Builder()
            .url(url)
            .post(body.toRequestBody("application/x-www-form-urlencoded".toMediaType()))
            .build()
        okHttpClient.newCall(request).execute().use {
            if (it.code == 200) {
                val respBody = it.body?.string()
                println("Nacos返回：$respBody")
                return respBody
            }
            return null
        }
    }

    /**
     * 发送http请求
     */
    fun httpGet(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()
        val respCall = okHttpClient.newCall(request).execute()

        if (respCall.code == 200) {
            return respCall.body?.string()
        }
        return null
    }
}