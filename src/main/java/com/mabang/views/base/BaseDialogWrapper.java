package com.mabang.views.base;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 父DialogWrapper
 */
public abstract class BaseDialogWrapper extends DialogWrapper {

    public static final Box rootBox = Box.createVerticalBox();

    public BaseDialogWrapper() {
        super(false);
        super.setResizable(this.resizable());
        super.setTitle(this.title());
        super.setSize(this.width(), this.height());
    }

    /**
     * 窗体内容
     */
    public abstract JComponent createPanel();

    /**
     * 标题
     */
    public abstract String title();

    /**
     * 默认窗体宽度
     */
    public int width() {
        return 400;
    }

    /**
     * 默认窗体高度
     */
    public int height() {
        return 600;
    }

    /**
     * 是否可以改变窗体大小
     */
    public boolean resizable() {
        return true;
    }

    /**
     * 窗体渲染
     */
    @Override
    public @Nullable JComponent createCenterPanel() {
        return createPanel();
    }

    /**
     * 隐藏默认OK和Cancel按钮
     */
    @Override
    public Action @NotNull [] createActions() {
        return new Action[]{};
    }

    public static class BaseWindow extends WindowAdapter {

        @Override
        public void windowClosed(WindowEvent e) {

        }
    }

}
