package com.mabang.utils;

import com.intellij.openapi.ui.Messages;

/**
 * TODO:
 */
public class MessageUtils {
    public static void showInfoMessage(String message) {
        Messages.showInfoMessage(message, "提示");
    }

    public static void showErrorMessage(String message) {
        Messages.showInfoMessage(message, "错误");
    }
}
