package com.github.nanbiango.views.base

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.Action
import javax.swing.Box
import javax.swing.JComponent

/**
 * 自定义跟视图
 */
abstract class CustomRootView(viewTitle: String, private val width: Int = 400, private val height: Int = 600) : DialogWrapper(true) {

    protected val rootBox: Box = Box.createVerticalBox()

    init {
        super.init()
        title = viewTitle
        isResizable = false
    }

    /**
     * 放入根Box
     */
    override fun createCenterPanel(): JComponent {
        setSize(width, height)
        return rootBox
    }

    /**
     * 隐藏默认OK和Cancel按钮
     */
    override fun createActions(): Array<Action> {
        return emptyArray()
    }
}