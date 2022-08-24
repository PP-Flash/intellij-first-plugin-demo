package com.github.nanbiango.component

import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.ui.EditorTextField

/**
 * EditorTextField的扩展
 */
open class EditTextFieldPlus(val oneLine: Boolean = false) : EditorTextField() {

    private val serialVersionUID: Long = -7437595304971227487L

    /**
     * 占位符内容
     */
    var placeholder: String = ""

    /**
     * 重写并增加滚动条
     */
    override fun createEditor(): EditorEx {
        val ce = super.createEditor()
        ce.setHorizontalScrollbarVisible(true)
        ce.setVerticalScrollbarVisible(true)
        ce.setPlaceholder(placeholder)
        ce.isOneLineMode = oneLine
        return ce
    }
}