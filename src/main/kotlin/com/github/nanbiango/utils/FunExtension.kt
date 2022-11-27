package com.github.nanbiango.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.regex.Pattern

/**
 * 函数的扩展
 */
class FunExtension


/**
 * 基于正则表达式的校验扩展,函数本身为正则表达式
 *
 * @param value 需要匹配的值
 */
fun String.regularMatch(value: String): Boolean {
    return Pattern.compile(this).matcher(value).matches()
}

fun Any.toJson(): String {
    return GlobalConstant.gson.toJson(this)
}

fun Any.toJsonPretty(): String {
    return GlobalConstant.gsonPretty.toJson(this)
}

fun <T> String.fromJson(clazz: Class<T>): T {
    return GlobalConstant.gson.fromJson(this, clazz)
}