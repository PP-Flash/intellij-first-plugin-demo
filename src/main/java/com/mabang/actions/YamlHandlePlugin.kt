package com.github.nanbiango.actions

import com.github.nanbiango.views.YamlFormatView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * ...
 */
class YamlHandlePlugin : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        YamlFormatView().show()
    }
}