package com.github.nanbiango.actions

import com.github.nanbiango.views.gui.MabangAESView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * 时间戳处理
 */
class MabangAESDecryptHandlePlugin : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        MabangAESView("马帮AES解密", 600, 450).show()
    }
}