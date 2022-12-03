package com.mabang.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mabang.views.gui.YamlFormatView;
import org.jetbrains.annotations.NotNull;

/**
 * Yaml文件处理
 */
public class YamlFileAction extends AnAction {

    public final YamlFormatView yamlFormatView = new YamlFormatView();

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        yamlFormatView.show();
    }
}
