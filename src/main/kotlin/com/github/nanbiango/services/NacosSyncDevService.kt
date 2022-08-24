package com.github.nanbiango.services

import com.github.nanbiango.utils.Utils
import com.google.gson.JsonParser

/**
 * 针对Nacos的信息同步服务
 */
object NacosSyncDevService {

    private const val SERVICE_URL = "https://dev-nacos.mabangerp.com"

    /**
     * 发布配置
     */
    fun publishConfig(
        content: String,
        dataId: String,
        group: String = "DEFAULT_GROUP",
        namespaceId: String = "",
        type: String = "yaml"
    ) {
        val accessToken = login()
        //发布配置
        val result = Utils.httpFromPost(
            "$SERVICE_URL/nacos/v1/cs/configs",
            "accessToken=${accessToken}&content=${content}&dataId=${dataId}&group=${group}&tenant=${namespaceId}&type=${type}"
        )
        println("发布配置结果：$result")
    }

    /**
     * 获取配置
     */
    fun getConfig(dataId: String, group: String, namespaceId: String = "public") {
        val accessToken = login()
        //查询配置
        val result =
            Utils.httpGet("$SERVICE_URL/nacos/v1/cs/configs?accessToken=${accessToken}&dataId=${dataId}&group=${group}&tenant=${namespaceId}")

        println("查询的配置：${result}")
    }

    /**
     * 登录
     *
     * @return 返回AccessToken
     */
    private fun login(): String {
        val result = Utils.httpFromPost("$SERVICE_URL/nacos/v1/auth/login", "username=mdc&password=mdc123456")
        return JsonParser.parseString(result).asJsonObject.get("accessToken").asString
    }
}