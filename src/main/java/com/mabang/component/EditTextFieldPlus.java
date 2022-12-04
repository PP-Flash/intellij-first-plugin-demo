package com.mabang.component;

import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.ui.EditorTextField;

/**
 * TODO: desc
 *
 * @author wangchenglong
 * @since 2022-12-04
 */
public class EditTextFieldPlus extends EditorTextField {

    private String placeholder;
    private boolean oneLine;

    public EditTextFieldPlus(String placeholder, boolean oneLine) {
        this.placeholder = placeholder;
        this.oneLine = oneLine;
    }

    public EditTextFieldPlus(boolean oneLine, String placeholder) {
        this.oneLine = oneLine;
        this.placeholder = placeholder;
    }

    public EditTextFieldPlus() {
    }

    public EditorEx createEditor() {
        var ce = super.createEditor();
        ce.setHorizontalScrollbarVisible(true);
        ce.setVerticalScrollbarVisible(true);
        ce.setPlaceholder(placeholder);
        ce.setOneLineMode(oneLine);
        return ce;
    }
}
