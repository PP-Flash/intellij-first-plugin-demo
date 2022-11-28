package com.github.nanbiango.views

import com.github.nanbiango.component.EditTextFieldPlus
import com.github.nanbiango.utils.showErrorMessage
import com.github.nanbiango.utils.showInfoMessage
import com.github.nanbiango.utils.toJson
import com.github.nanbiango.views.base.CustomRootView
import com.google.gson.JsonParser
import com.intellij.openapi.diagnostic.thisLogger
import org.apache.commons.lang3.StringUtils
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent

/**
 * JSON数据的处理
 */
class JsonFormatView : CustomRootView("Json文件处理") {

    private val etf: EditTextFieldPlus = EditTextFieldPlus()
    private val checkFileBtn: JButton = JButton("检查格式")
    private val formatBtn: JButton = JButton("格式化")
    private val topBox: Box = Box.createHorizontalBox()
    private val bottomBox: Box = Box.createHorizontalBox()

    init {
        super.init()
        //属性初始化
        this.initComponent()
    }

    private fun initComponent() {
        //创建编辑器
        etf.placeholder = "请输入Json格式内容..."
        //检索按钮监听点击事件
        checkFileBtn.addActionListener {
            val jsonText = etf.text
            if (StringUtils.isEmpty(jsonText)) {
                "内容不能为空".showInfoMessage()
                return@addActionListener
            }
            try {
                JsonParser.parseString(jsonText)
                "格式校验通过".showInfoMessage()
            } catch (e: Exception) {
                (e.message ?: "格式校验异常").showErrorMessage()
            }
        }
        //格式化内容点击事件
        formatBtn.addActionListener {
            val jsonText = etf.text
            if (StringUtils.isEmpty(jsonText)) {
                "内容不能为空".showInfoMessage()
                return@addActionListener
            }
            try {
                etf.text = JsonParser.parseString(jsonText).toJson()
            } catch (e: Exception) {
                thisLogger().error("Json处理异常", e)
                (e.message ?: "格式异常，格式化失败").showErrorMessage()
            }
        }

        //装填文本框和底部按钮
        topBox.add(etf)
        bottomBox.add(checkFileBtn)
        bottomBox.add(formatBtn)
        //追加到根Box
        rootBox.add(topBox)
        rootBox.add(bottomBox)
    }

    override fun createPanel(): JComponent {
        return rootBox
    }
}