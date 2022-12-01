package com.mabang.views.base;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 父DialogWrapper
 */
public abstract class BaseDialogWrapper extends DialogWrapper {

    protected static final Box rootBox = Box.createVerticalBox();


    protected BaseDialogWrapper() {
        super(true);
        super.setResizable(this.resizable());
        super.setTitle(this.title());
        super.setSize(this.width(), this.height());
    }

    /**
     * 窗体内容
     */
    protected abstract JComponent createPanel();

    /**
     * 标题
     */
    protected abstract String title();

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
    protected boolean resizable() {
        return true;
    }

    /**
     * 窗体渲染
     */
    @Override
    protected @Nullable JComponent createCenterPanel() {
        return createPanel();
    }

    /**
     * 隐藏默认OK和Cancel按钮
     */
    @Override
    protected Action @NotNull [] createActions() {
        return new Action[]{};
    }

    public static class BaseWindow extends WindowAdapter {

        @Override
        public void windowClosed(WindowEvent e) {

        }
    }

}
