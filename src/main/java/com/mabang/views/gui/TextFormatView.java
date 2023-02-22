package com.mabang.views.gui;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Splitter;
import com.mabang.component.EditTextFieldPlus;
import com.mabang.utils.MessageUtils;
import com.mabang.views.base.BaseDialogWrapper;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.Collectors;

/**
 * Json数据处理视图
 */
public class TextFormatView extends BaseDialogWrapper {

    public EditTextFieldPlus etfLeft = new EditTextFieldPlus(false, "请输入需要处理的内容...");
    public EditTextFieldPlus etfRight = new EditTextFieldPlus();
    public JLabel transferLabel = new JLabel("  <=>  ");
    public JButton addCommaBtn = new JButton("增加逗号分隔符");
    public JButton clearBtn = new JButton("清空文本");
    public Box topBox = Box.createHorizontalBox();
    public Box bottomBox = Box.createHorizontalBox();

    public TextFormatView() {
        this.initComponent();
        super.init();
    }

    @Override
    public JComponent createPanel() {
        return rootBox;
    }

    @Override
    public String title() {
        return "文本数据处理";
    }

    @Override
    public int width() {
        return 900;
    }

    @Override
    public int height() {
        return 700;
    }

    private void initComponent() {
        //文本逗号处理
        addCommaBtn.addActionListener(e -> this.textCommaHandle(etfLeft.getText().trim()));
        //清空
        clearBtn.addActionListener(e -> {
            etfLeft.setText("");
            etfRight.setText("");
        });
        getWindow().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                etfLeft.setText("");
                etfRight.setText("");
            }
        });

        //装填文本框和底部按钮
        topBox.add(etfLeft);
        topBox.add(transferLabel);
        topBox.add(etfRight);

        bottomBox.add(addCommaBtn);
        bottomBox.add(clearBtn);
        //追加到根Box
        rootBox.add(topBox);
        rootBox.add(bottomBox);
    }

    /**
     * 文本逗号处理
     */
    private void textCommaHandle(String text) {
        if (StrUtil.isEmpty(text)) {
            MessageUtils.showErrorMessage("内容不能为空");
            return;
        }
        try {
            etfRight.setText(Splitter.on("\n").omitEmptyStrings()
                    .splitToStream(text)
                    .collect(Collectors.joining(",")));
        } catch (Exception e) {
            etfRight.setText("处理异常:" + e.getMessage());
        }
    }
}
