package com.github.nanbiango.actions

import com.github.nanbiango.views.YamlFormatViewCustom
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * ...
 */
class YamlHandlePlugin : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        YamlFormatViewCustom().show()
    }
}