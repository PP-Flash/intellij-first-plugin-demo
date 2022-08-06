package com.github.nanbiango.intellijfirstplugindemo.services

import com.intellij.openapi.project.Project
import com.github.nanbiango.intellijfirstplugindemo.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
