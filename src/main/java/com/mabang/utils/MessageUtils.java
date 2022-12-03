package com.mabang.utils;

import com.intellij.openapi.ui.Messages;

/**
 * 消息工具类
 */
public class MessageUtils {
    public static void showInfoMessage(String message) {
        Messages.showInfoMessage(message, "提示");
    }

    public static void showErrorMessage(String message) {
        Messages.showInfoMessage(message, "错误");
    }
}
