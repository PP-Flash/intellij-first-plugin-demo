package com.github.nanbiango.views

import com.github.nanbiango.component.EditTextFieldPlus
import com.github.nanbiango.services.NacosSyncDevService
import com.github.nanbiango.utils.Utils
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.nio.file.Files
import javax.swing.Action
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent

/**
 * Nacos同步配置视图
 */
class NacosSyncView(val project: Project) : DialogWrapper(true) {
    private val etf: EditTextFieldPlus = EditTextFieldPlus()
    private val topBox: Box = Box.createHorizontalBox()
    private val bottomBox: Box = Box.createHorizontalBox()
    private val rootBox: Box = Box.createVerticalBox()
    private val syncConfigBtn: JButton = JButton("同步配置到Dev")

    init {
        super.init()
        super.setResizable(false)
        title = "Nacos配置管理"
        setSize(500, 600)
        //初始化组件
        this.initComponent()
        //文件内容读取
        runBlocking { readConfigFile() }
    }

    private fun syncConfigToDev() {
        val yamlText = etf.text
        if (StringUtils.isEmpty(yamlText)) {
            Utils.showMessage("配置内容不能为空")
            return
        }
        try {
            //校验并加载yaml配置
            val yamlConfig = Yaml().load<String>(yamlText)
            NacosSyncDevService.publishConfig(yamlConfig!!, "", "", "")
        } catch (e: Exception) {
            Utils.showErrorMessage(e.message ?: "格式校验异常")
        }
    }

    private fun initComponent() {
        topBox.add(etf)

        bottomBox.add(syncConfigBtn)

        rootBox.add(topBox)
        rootBox.add(bottomBox)
    }

    private suspend fun readConfigFile() = coroutineScope {
        launch {
            //异步文件扫描
            val configFile = FileUtils.listFiles(File(project.basePath!!), arrayOf("yml", "yaml"), true)
                .firstOrNull { it.name.contains("bootstrap-loc") }
            //存在文件则读取
            if (configFile?.exists() == true) {
                etf.text = Files.newBufferedReader(configFile.toPath()).readText()
            }
        }
    }

    /**
     * 创建可视化界面
     */
    override fun createCenterPanel(): JComponent {
        return rootBox
    }

    /**
     * 隐藏默认OK和Cancel按钮
     */
    override fun createActions(): Array<Action> {
        return emptyArray()
    }
}