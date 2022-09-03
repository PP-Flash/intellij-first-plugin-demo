package com.github.nanbiango.actions

import com.github.nanbiango.views.NacosSyncViewCustom
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * Nacos配置视图话管理
 */
class NacosHandlePlugin : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        NacosSyncViewCustom(e.project!!).show()
    }
}