package com.github.nanbiango.views

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.alibaba.fastjson2.JSONWriter
import com.github.nanbiango.component.EditTextFieldPlus
import com.github.nanbiango.utils.showErrorMessage
import com.github.nanbiango.utils.showInfoMessage
import com.github.nanbiango.views.base.CustomRootView
import org.apache.commons.lang3.StringUtils
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel


/**
 * JSON数据的处理
 */
class JsonFormatView(viewTitle: String, width: Int, height: Int) : CustomRootView(viewTitle, width, height) {

    private val viewWidth = width

    companion object {
        val etfLeft: EditTextFieldPlus = EditTextFieldPlus(placeholder = "请输入Json格式内容...")
        val etfRight: EditTextFieldPlus = EditTextFieldPlus()
        val transferLabel = JLabel("  <=>  ")
        val formatBtn = JButton("格式化")
        val jsonEscapeBtn = JButton("Json转义")
        val clearBtn = JButton("清空文本")
        val topBox: Box = Box.createHorizontalBox()
        val bottomBox: Box = Box.createHorizontalBox()
    }

    init {
        super.init()
        //属性初始化
        this.initComponent()
        //监听窗口关闭
        window.addWindowListener(object : WindowAdapter() {
            override fun windowClosed(e: WindowEvent) {
                etfLeft.text = ""
                etfRight.text = ""
            }
        })
    }

    private fun initComponent() {
        //格式化内容点击事件
        formatBtn.addActionListener { this.formatJsonHandle(etfLeft.text) }
        //Json转义
        jsonEscapeBtn.addActionListener { this.escapeJsonHandle(etfLeft.text) }
        //清空
        clearBtn.addActionListener {
            etfLeft.text = ""
            etfRight.text = ""
        }

        //装填文本框和底部按钮
        topBox.add(etfLeft)
        topBox.add(transferLabel)
        topBox.add(etfRight)

        bottomBox.add(formatBtn)
        bottomBox.add(jsonEscapeBtn)
        bottomBox.add(clearBtn)
        //追加到根Box
        rootBox.add(topBox)
        rootBox.add(bottomBox)
    }

    /**
     * 格式化Json
     */
    private fun formatJsonHandle(jsonText: String) {
        if (StringUtils.isEmpty(jsonText)) {
            "内容不能为空".showInfoMessage()
            return
        }
        if (!JSON.isValid(jsonText)) {
            "Json格式不合法".showErrorMessage()
            return
        }
        if (JSON.isValidArray(jsonText)) {
            etfRight.text = JSON.parseArray(jsonText).toJSONString(JSONWriter.Feature.PrettyFormat)
        } else {
            etfRight.text = JSON.parseObject(jsonText).toJSONString(JSONWriter.Feature.PrettyFormat)
        }
    }


    private fun escapeJsonHandle(jsonText: String) {
        if (StringUtils.isEmpty(jsonText)) {
            "内容不能为空".showInfoMessage()
            return
        }
        if (!JSON.isValid(jsonText)) {
            "Json格式不合法".showErrorMessage()
            return
        }
        val rootList = ArrayList<Map<String, Any>>()
        if (JSON.isValidArray(jsonText)) {
            JSON.parseArray(jsonText, JSONObject::class.java).forEach { jsonObj -> rootList.add(this.escapeJson(jsonObj.toJSONString())) }
            etfRight.text = JSON.toJSONString(rootList, JSONWriter.Feature.PrettyFormat)
        } else {
            etfRight.text = JSON.toJSONString(this.escapeJson(jsonText), JSONWriter.Feature.PrettyFormat)
        }
    }

    /**
     * 转义Json对象
     */
    private fun escapeJson(jsonText: String): Map<String, Any> {
        val jsonMap = HashMap<String, Any>()
        if (JSON.isValidObject(jsonText)) {
            JSON.parseObject(jsonText).forEach { key, value ->
                val v = value.toString()
                if (JSON.isValidObject(v)) {
                    jsonMap[key] = this.escapeJson(v)
                    return@forEach
                }
                if (JSON.isValidArray(v)) {
                    val list = ArrayList<Map<String, Any>>()
                    JSONArray.parseArray(v)
                        .toList(JSONObject::class.java)
                        .forEach { json -> list.add(this.escapeJson(json.toJSONString())) }
                    jsonMap[key] = list
                    return@forEach
                }
                jsonMap[key] = v
            }
        }
        return jsonMap
    }

    override fun createPanel(): JComponent {
        return rootBox
    }
}