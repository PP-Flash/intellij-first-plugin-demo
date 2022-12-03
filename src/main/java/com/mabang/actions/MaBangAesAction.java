package com.mabang.actions;

import com.github.nanbiango.views.gui.MabangAESView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 马帮AES处理
 *
 * @author wangchenglong
 * @since 2022-12-01
 */
public class MaBangAesAction extends AnAction {

    public final MabangAESView mabangAESView = new MabangAESView();

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        mabangAESView.show();
    }
}
