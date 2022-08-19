package com.github.nanbiango.views

import com.intellij.ide.util.RunOnceUtil
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.Box
import javax.swing.JComponent

/**
 * Nacos同步配置视图
 */
class NacosSyncView : DialogWrapper(true) {
    private val topBox: Box = Box.createHorizontalBox()
    private val bottomBox: Box = Box.createHorizontalBox()
    private val rootBox: Box = Box.createVerticalBox()

    /**
     * 创建可视化界面
     */
    override fun createCenterPanel(): JComponent {

        return rootBox
    }

}