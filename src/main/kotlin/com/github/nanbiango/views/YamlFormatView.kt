package com.github.nanbiango.views

import com.github.nanbiango.component.EditTextFieldPlus
import com.github.nanbiango.utils.showErrorMessage
import com.github.nanbiango.utils.showInfoMessage
import com.github.nanbiango.views.base.CustomRootView
import org.apache.commons.lang3.StringUtils
import org.yaml.snakeyaml.Yaml
import javax.swing.Box
import javax.swing.JButton

/**
 * Yaml数据
 */
class YamlFormatView : CustomRootView("Yaml文件处理") {

    private val etf: EditTextFieldPlus = EditTextFieldPlus()
    private val checkFileBtn: JButton = JButton("检查格式")
    private val topBox: Box = Box.createHorizontalBox()
    private val bottomBox: Box = Box.createHorizontalBox()

    init {
        //属性初始化
        this.initComponent()
    }

    private fun initComponent() {
        //创建编辑器
        etf.placeholder = "请输入需要校验的Yaml格式内容..."
        //检索按钮监听点击事件
        checkFileBtn.addActionListener {
            val yamlText = etf.text
            if (StringUtils.isEmpty(yamlText)) {
                "内容不能为空".showInfoMessage()
                return@addActionListener
            }
            try {
                Yaml().load<String>(yamlText)
                "格式校验通过".showInfoMessage()
            } catch (e: Exception) {
                (e.message ?: "格式校验异常").showErrorMessage()
            }
        }
        //装填文本框和底部按钮
        topBox.add(etf)
        bottomBox.add(checkFileBtn)

        //追加到根Box
        rootBox.add(topBox)
        rootBox.add(bottomBox)
    }
}