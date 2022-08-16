package com.chenglong.tools

import com.github.nanbiango.utils.Utils

/**
 * ...
 */
fun main() {
    val resp = Utils.httpGet("https://baidu.com")
    println(resp)
}