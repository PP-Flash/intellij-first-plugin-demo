package com.github.nanbiango.actions

import com.github.nanbiango.views.TimeUnixFormatView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * 时间戳处理
 */
class TimeUnixHandlePlugin : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        TimeUnixFormatView().show()
    }
}