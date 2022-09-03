package com.github.nanbiango.actions

import com.github.nanbiango.views.JsonFormatViewCustom
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * JSON格式内容的处理
 */
class JsonHandlePlugin : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        JsonFormatViewCustom().show()
    }
}