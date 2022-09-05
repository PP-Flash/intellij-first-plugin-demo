package com.github.nanbiango.component

import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.ui.EditorTextField

/**
 * EditorTextField的扩展
 */
open class EditTextFieldPlus(private var oneLine: Boolean = false, var placeholder: String = "") : EditorTextField() {

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