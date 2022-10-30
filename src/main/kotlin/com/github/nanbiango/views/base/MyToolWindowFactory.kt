package com.github.nanbiango.views.base

import com.github.nanbiango.views.gui.TimestampUnixView
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

/**
 * 工具窗口
 *
 * @author wangchenglong
 * @since 2022-10-30
 */
class MyToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory: ContentFactory = ContentFactory.SERVICE.getInstance()
        val createContent = contentFactory.createContent(TimestampUnixView().getMainPanel(), "时间戳", false)
        toolWindow.contentManager.addContent(createContent)
    }
}