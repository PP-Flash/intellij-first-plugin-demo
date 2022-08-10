package com.github.nanbiango.as;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

/**
 * ...
 */
public class MyFirstPlugin extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Messages.showMessageDialog("这是自定义插件文本", "提示", Messages.getInformationIcon());
    }
}
