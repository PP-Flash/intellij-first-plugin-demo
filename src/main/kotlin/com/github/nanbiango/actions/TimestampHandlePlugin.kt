package com.github.nanbiango.actions

import com.github.nanbiango.views.gui.TimestampUnixView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * 时间戳处理
 */
class TimestampHandlePlugin : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        TimestampUnixView().show()
    }
}