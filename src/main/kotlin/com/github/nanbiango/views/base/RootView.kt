package com.github.nanbiango.views.base

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.Action
import javax.swing.Box
import javax.swing.JComponent

abstract class RootView(viewTitle: String, width: Int = 400, height: Int = 600) : DialogWrapper(true) {

    protected val rootBox: Box = Box.createVerticalBox()

    init {
        super.init()
        title = viewTitle
        isResizable = false
        setSize(width, height)
    }

    /**
     * 放入根Box
     */
    override fun createCenterPanel(): JComponent {
        return rootBox
    }

    /**
     * 隐藏默认OK和Cancel按钮
     */
    override fun createActions(): Array<Action> {
        return emptyArray()
    }
}