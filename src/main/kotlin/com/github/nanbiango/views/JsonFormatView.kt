package com.github.nanbiango.views

import com.github.nanbiango.component.EditTextFieldPlus
import com.github.nanbiango.utils.Utils
import com.github.nanbiango.views.base.CustomRootView
import com.google.gson.JsonParser
import com.intellij.openapi.diagnostic.thisLogger
import org.apache.commons.lang3.StringUtils
import javax.swing.Box
import javax.swing.JButton

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
                Utils.showMessage("内容不能为空")
                return@addActionListener
            }
            try {
                JsonParser.parseString(jsonText)
                Utils.showMessage("格式校验通过")
            } catch (e: Exception) {
                Utils.showErrorMessage(e.message ?: "格式校验异常")
            }
        }
        //格式化内容点击事件
        formatBtn.addActionListener {
            val jsonText = etf.text
            if (StringUtils.isEmpty(jsonText)) {
                Utils.showMessage("内容不能为空")
                return@addActionListener
            }
            try {
                etf.text = Utils.gson.toJson(JsonParser.parseString(jsonText))
            } catch (e: Exception) {
                thisLogger().error("Json处理异常", e)
                Utils.showErrorMessage(e.message ?: "格式异常，格式化失败")
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

}