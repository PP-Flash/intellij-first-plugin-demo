package com.github.nanbiango.views

import com.github.nanbiango.component.EditTextFieldPlus
import com.github.nanbiango.utils.Utils
import com.github.nanbiango.utils.regularMatch
import com.github.nanbiango.views.base.RootView
import com.intellij.ui.components.JBTextField
import java.awt.GridLayout
import java.awt.event.ActionListener
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JPanel

/**
 * 时间戳的统一转换
 */
class TimeUnixFormatView : RootView("时间戳转换", 600, 300) {

    private val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private val inputTextField: JBTextField = JBTextField("输入时间或时间戳：")
    private val timeTextFieldL1: JBTextField = JBTextField()
    private val timeTextFieldR1: JBTextField = JBTextField()
    private val transferTimeBtn: JButton = JButton("格式转换")

    private val topPanel: JPanel = JPanel(GridLayout(1, 2))

    //文本输入框
    private val inputEdit: EditTextFieldPlus = EditTextFieldPlus(true)
    private val timeTextEditL1: EditTextFieldPlus = EditTextFieldPlus(true)
    private val timestampTextEditR1: EditTextFieldPlus = EditTextFieldPlus(true)

    private val topBox: Box = Box.createHorizontalBox()
    private val b1Box: Box = Box.createHorizontalBox()

    init {
        //初始化组件属性
        this.initComponent()
    }

    private fun initComponent() {
        //格式转换点击事件
        transferTimeBtn.addActionListener(this.transferTimeBtnAction())

        //顶部布局
        topBox.add(inputTextField)
        topBox.add(inputEdit)
        topBox.add(transferTimeBtn)


        //展示时间第一行
        b1Box.add(timeTextFieldL1)
        b1Box.add(timeTextEditL1)
        b1Box.add(timeTextFieldR1)
        b1Box.add(timestampTextEditR1)


        rootBox.add(topBox)
        rootBox.add(b1Box)
    }

    /**
     * 时间转换事件
     */
    private fun transferTimeBtnAction() = ActionListener {
        try {
            val inputText = inputEdit.text
            //判断输入文本是否纯数字时间戳
            if ("//d+".regularMatch(inputText)) {
                timeTextFieldL1.text = "时间"
                val timestampText = inputText.toLong()
                //时间戳，10位是秒时间戳，13位是毫秒时间戳
                if (inputText.length > 10) {
                    timeTextFieldR1.text = "时间戳(秒)"
                    timestampTextEditR1.text = Instant.ofEpochMilli(timestampText).epochSecond.toString()
                } else {
                    timeTextFieldR1.text = "时间戳(毫秒)"
                    timestampTextEditR1.text = Instant.ofEpochMilli(timestampText).toEpochMilli().toString()
                }
                timeTextEditL1.text = Instant.ofEpochMilli(timestampText)
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ISO_DATE_TIME)
            } else {
                timeTextFieldL1.text = "时间戳(秒)"
                timeTextFieldR1.text = "时间戳(毫秒)"
                val dateTime = LocalDateTime.parse(inputText, timeFormatter).atZone(ZoneId.systemDefault())
                timeTextEditL1.text = dateTime.toInstant().epochSecond.toString()
                timestampTextEditR1.text = dateTime.toInstant().toEpochMilli().toString()
            }
        } catch (e: Exception) {
            Utils.showErrorMessage("时间转换异常：${e.message}")
        }
    }
}