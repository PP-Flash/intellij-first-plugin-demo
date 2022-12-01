package com.mabang.actions;

import com.github.nanbiango.views.JsonFormatView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Json处理
 *
 * @author wangchenglong
 * @since 2022-12-01
 */
public class JsonHandlePlugin extends AnAction {

    public static final JsonFormatView jsonFormatView = new JsonFormatView("Json文件处理", 900, 700);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        jsonFormatView.show();
    }
}
