package com.mabang.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mabang.views.gui.JsonFormatView;
import org.jetbrains.annotations.NotNull;

/**
 * Json处理
 *
 * @author wangchenglong
 * @since 2022-12-01
 */
public class JsonFormatAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        new JsonFormatView().show();
    }
}
