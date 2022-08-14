package com.github.nanbiango.component

import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.ui.EditorTextField

/**
 * ...
 */
class YamlEditTextField : EditorTextField() {
    companion object {
        private const val serialVersionUID: Long = -7195533177145313020L
    }

    /**
     * 重写并增加滚动条
     */
    override fun createEditor(): EditorEx {
        val ce = super.createEditor()
        ce.setHorizontalScrollbarVisible(true)
        ce.setVerticalScrollbarVisible(true)
        ce.setPlaceholder("请输入需要校验的Yaml格式文件...")
        ce.isOneLineMode = false
        return ce
    }


}