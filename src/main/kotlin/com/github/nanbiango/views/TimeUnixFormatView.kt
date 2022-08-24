package com.github.nanbiango.views

import com.github.nanbiango.component.EditTextFieldPlus
import javax.swing.Box
import javax.swing.JButton

/**
 * 时间戳的统一转换
 */
class TimeUnixFormatView() : RootView("时间戳转换") {

    //文本输入框
    private val inputEdit: EditTextFieldPlus = EditTextFieldPlus(true)
    private val transferTimeBtn: JButton = JButton("格式转换")
    private val topBox: Box = Box.createHorizontalBox()
    private val bottom1Box: Box = Box.createHorizontalBox()
    private val bottom2Box: Box = Box.createHorizontalBox()

    init {
        //初始化组件属性
        this.initComponent()
    }

    private fun initComponent() {
        topBox.add(inputEdit)
        topBox.add(transferTimeBtn)

//        bottom1Box.add()
//        bottom2Box.add()

        rootBox.add(topBox)
        rootBox.add(bottom1Box)
        rootBox.add(bottom2Box)
    }
}