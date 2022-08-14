package com.github.nanbiango.utils

import com.intellij.openapi.ui.Messages

/**
 * 扩展字符串消息
 */
object Utils {
    fun showMessage(message: String) = Messages.showInfoMessage(message, "提示")

    fun showErrorMessage(message: String) = Messages.showErrorDialog(message, "异常")
}