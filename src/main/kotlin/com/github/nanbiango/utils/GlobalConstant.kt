package com.github.nanbiango.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * 全局
 */
object GlobalConstant {
    val GSON: Gson = GsonBuilder().create()

    val GSON_PRETTY: Gson = GsonBuilder().setPrettyPrinting().create()
}