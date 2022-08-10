package com.github.nanbiango.services

import com.intellij.openapi.project.Project
import com.github.nanbiango.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
