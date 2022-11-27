package com.github.nanbiango.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * 全局
 */
object GlobalConstant {
    val gson: Gson = GsonBuilder().create()

    val gsonPretty: Gson = GsonBuilder().setPrettyPrinting().create()
}