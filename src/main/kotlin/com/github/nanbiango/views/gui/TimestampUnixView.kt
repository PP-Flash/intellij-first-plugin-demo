package com.github.nanbiango.views.gui

import com.github.nanbiango.utils.regularMatch
import com.github.nanbiango.utils.showErrorMessage
import com.github.nanbiango.utils.showInfoMessage
import com.github.nanbiango.views.base.CustomRootView
import java.awt.event.ActionListener
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

/**
 * 时间戳视图
 * 注意：如果采用类似 CustomRootView 类继承方式，form文件中的组件暂时无法完成初始化
 * 所以直接使用 DialogWrapper 类来编写
 *
 * @author wangchenglong
 * @since 2022-09-03
 */
class TimestampUnixView(viewTitle: String, width: Int, height: Int) : CustomRootView(viewTitle, width, height) {
    //最外层Panel面板
    private lateinit var timestampMainPanel: JPanel

    //时间转换按钮
    private lateinit var timeConvBtn: JButton

    //时间戳转换按钮
    private lateinit var timestampConvBtn: JButton

    //还原默认时间按钮
    private lateinit var rollbackAllBtn: JButton

    //输入的代转换时间戳文本
    private lateinit var timestampTxt: JTextField

    //输入的待转换时间文本
    private lateinit var timeTxt: JTextField

    //转换后时间（秒）
    private lateinit var afterTimeSecondText: JTextField

    //转换后时间（毫秒）
    private lateinit var afterTimeMillsText: JTextField

    //转换后时间戳(秒)
    private lateinit var afterTimestampSecondText: JTextField

    //转换后时间戳(毫秒)
    private lateinit var afterTimestampMillsText: JTextField

    private val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    init {
        super.init()
        //初始化默认的实例文本
        this.initTime()
        //按钮事件注册
        timeConvBtn.addActionListener(timeConv())
        timestampConvBtn.addActionListener(timestampConv())
        rollbackAllBtn.addActionListener { this.initTime() }
    }

    /**
     * idea工具窗口的方式出现
     */
    fun getMainPanel(): JPanel {
        return timestampMainPanel
    }

    /**
     * 初始化时间
     */
    private fun initTime() {
        timeTxt.text = LocalDateTime.now().format(timeFormat)
        timestampTxt.text = Instant.now().toEpochMilli().toString()
        afterTimeSecondText.text = ""
        afterTimeMillsText.text = ""
        afterTimestampSecondText.text = ""
        afterTimestampMillsText.text = ""
    }

    /**
     * 时间转换
     */
    private fun timeConv() = ActionListener {
        val timeText = timeTxt.text
        if (timeText.isNullOrEmpty()) {
            "时间文本为空".showErrorMessage()
            return@ActionListener
        }
        try {
            val localTime = LocalDateTime.parse(timeText, timeFormat).atZone(ZoneId.systemDefault()).toInstant()
            afterTimestampMillsText.text = localTime.toEpochMilli().toString()
            afterTimestampSecondText.text = localTime.epochSecond.toString()
        } catch (e: Exception) {
            "时间格式错误:${e.message}".showErrorMessage()
            return@ActionListener
        }
    }

    /**
     * 时间戳转换
     */
    private fun timestampConv() = ActionListener {
        val timestampText = timestampTxt.text
        if (timestampText.isNullOrEmpty()) {
            "时间戳文本为空".showInfoMessage()
            return@ActionListener
        }
        if (!"\\d+".regularMatch(timestampText)) {
            "时间戳文本非纯数字".showErrorMessage()
            return@ActionListener
        }
        val timestamp = timestampText.toLong()
        afterTimeMillsText.text = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).format(timeFormat)
        afterTimeSecondText.text = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault()).format(timeFormat)
    }

    /**
     * 放入根Box
     */
    override fun createCenterPanel(): JComponent {
        return timestampMainPanel
    }
}