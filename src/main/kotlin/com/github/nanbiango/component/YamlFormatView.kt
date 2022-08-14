package com.github.nanbiango.component

import com.github.nanbiango.utils.Utils
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.EditorTextField
import com.intellij.ui.components.JBPanel
import org.apache.commons.lang3.StringUtils
import org.yaml.snakeyaml.Yaml
import java.awt.FlowLayout
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

/**
 * Yaml数据
 */
class YamlFormatView : DialogWrapper(true) {

    private val etf: YamlEditTextField = YamlEditTextField()
    private val checkFileBtn: JButton = JButton("检查格式")
    private val topBox: Box = Box.createHorizontalBox()
    private val bottomBox: Box = Box.createHorizontalBox()
    private val rootBox: Box = Box.createVerticalBox()

    init {
        super.init()
        title = "Yaml文件处理"
        setSize(400, 600)
        setResizable(false)

        //属性初始化
        this.initAttribute()
    }

    private fun initAttribute() {
        //创建编辑器
        etf.setOneLineMode(false)

        //检索按钮监听点击事件
        checkFileBtn.addActionListener {
            val yamlText = etf.text
            if (StringUtils.isEmpty(yamlText)) {
                Utils.showMessage("内容不能为空")
                return@addActionListener
            }
            try {
                Yaml().load<String>(yamlText)
                Utils.showMessage("格式校验通过")
            } catch (e: Exception) {
                Utils.showErrorMessage(e.message ?: "格式校验异常")
            }
        }
        //装填文本框和底部按钮
        topBox.add(etf)
        bottomBox.add(checkFileBtn)

        //追加到根Box
        rootBox.add(topBox)
        rootBox.add(bottomBox)
    }

    /**
     * 隐藏默认OK和Cancel按钮
     */
    override fun createActions(): Array<Action> {
        return emptyArray()
    }

    /**
     * 创建Dialog的内容部分
     */
    override fun createCenterPanel(): JComponent {
        return rootBox
    }


}