package com.github.nanbiango.views.gui;

import com.github.nanbiango.views.base.CustomRootView;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Json数据格式处理
 */
public class JsonHandleView extends CustomRootView {
    private JTextArea jsonOriginArea;
    private JTextArea jsonResultArea;
    private JButton btnFormat;
    private JButton btnJsonEscape;
    private JButton btnJsonToJavaClass;


    public JsonHandleView(@NotNull String viewTitle, int width, int height) {
        super(viewTitle, width, height);
    }

}
