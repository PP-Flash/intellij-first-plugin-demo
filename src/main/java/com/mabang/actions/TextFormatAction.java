package com.mabang.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mabang.views.gui.TextFormatView;
import org.jetbrains.annotations.NotNull;

/**
 * Json处理
 *
 * @author wangchenglong
 * @since 2022-12-01
 */
public class TextFormatAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        new TextFormatView().show();
    }
}
