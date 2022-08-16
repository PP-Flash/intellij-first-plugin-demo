package com.github.nanbiango.views

import com.alibaba.fastjson.JSON
import com.github.nanbiango.component.EditTextFieldPlus
import com.github.nanbiango.utils.Utils
import com.intellij.openapi.ui.DialogWrapper
import org.apache.commons.lang3.StringUtils
import javax.swing.Action
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent

/**
 * JSON数据的处理
 */
class JsonFormatView : DialogWrapper(true) {

    private val etf: EditTextFieldPlus = EditTextFieldPlus()
    private val checkFileBtn: JButton = JButton("检查格式")
    private val formatBtn: JButton = JButton("格式化")
    private val topBox: Box = Box.createHorizontalBox()
    private val bottomBox: Box = Box.createHorizontalBox()
    private val rootBox: Box = Box.createVerticalBox()

    init {
        super.init()
        title = "Json文件处理"
        setSize(400, 600)
        setResizable(false)

        //属性初始化
        this.initAttribute()
    }

    /**
     * 创建Dialog的内容部分
     */
    override fun createCenterPanel(): JComponent? {
        return rootBox
    }

    /**
     * 隐藏默认OK和Cancel按钮
     */
    override fun createActions(): Array<Action> {
        return emptyArray()
    }

    private fun initAttribute() {
        //创建编辑器
        etf.placeholder = "请输入需要校验的Yaml格式文件..."
        //检索按钮监听点击事件
        checkFileBtn.addActionListener {
            val jsonText = etf.text
            if (StringUtils.isEmpty(jsonText)) {
                Utils.showMessage("内容不能为空")
                return@addActionListener
            }
            try {
                JSON.parseObject(jsonText)
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
                etf.text = JSON.toJSONString(JSON.parseObject(jsonText), true)
            } catch (e: Exception) {
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