package com.github.nanbiango.views

import com.intellij.ide.util.RunOnceUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogWrapper
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JComponent
import kotlin.io.path.name

/**
 * Nacos同步配置视图
 */
class NacosSyncView(val project: Project) : DialogWrapper(true) {
    private val topBox: Box = Box.createHorizontalBox()
    private val bottomBox: Box = Box.createHorizontalBox()
    private val rootBox: Box = Box.createVerticalBox()
    private val syncConfigBtn: JButton = JButton("同步配置到Dev")

    init {
        super.init()
        super.setResizable(false)
        title = "Nacos配置管理"
        setSize(500, 600)


    }

    fun readConfigFile() {
        //异步文件扫描
        val configOptional = Files.list(Paths.get(project.basePath!!, "src/main/resources"))
            .filter { it.name == "bootstrap-loc.yml" }
            .findFirst()
        //存在文件则读取
        if (configOptional.isPresent) {
            val fileText = Files.newBufferedReader(configOptional.get()).readText()
            println(fileText)
        }
    }

    /**
     * 创建可视化界面
     */
    override fun createCenterPanel(): JComponent {
        println(ProjectManager.getInstance().defaultProject)
        return rootBox
    }

}