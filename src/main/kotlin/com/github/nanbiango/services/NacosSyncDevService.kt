package com.github.nanbiango.services

import com.alibaba.fastjson.JSON
import com.github.nanbiango.utils.Utils

/**
 * 针对Nacos的信息同步服务
 */
object NacosSyncDevService {

    private const val SERVICE_URL = "https://dev-nacos.mabangerp.com"

    /**
     * 发布配置
     */
    fun publishConfig(content: String, dataId: String, group: String) {
        val accessToken = login()
        //发布配置
        val result = Utils.httpPost("$SERVICE_URL/nacos/v1/cs/configs", "accessToken=${accessToken}&content=${content}&dataId=${dataId}&group=${group}")

        println("发布配置结果：${result}")
    }

    fun queryConfig(dataId: String, group: String) {
        val accessToken = login()
        //查询配置
        val result = Utils.httpGet("$SERVICE_URL/nacos/v1/cs/configs?accessToken=${accessToken}&dataId=${dataId}&group=${group}")

        println("当前配置：${result}")
    }

    /**
     * 登录
     *
     * @return 返回AccessToken
     */
    private fun login(): String {
        val result = Utils.httpFromPost("$SERVICE_URL/nacos/v1/auth/login", "username=mdc&password=mdc123456")
        return JSON.parseObject(result).getString("accessToken")
    }
}