package com.mabang.views.gui;

import cn.hutool.core.util.StrUtil;
import com.mabang.component.EditTextFieldPlus;
import com.mabang.utils.MessageUtils;
import com.mabang.views.base.BaseDialogWrapper;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Yaml数据
 */
public class YamlFormatView extends BaseDialogWrapper {

    private final EditTextFieldPlus etf = new EditTextFieldPlus();
    private final JButton checkFileBtn = new JButton("检查格式");
    private final Box topBox = Box.createHorizontalBox();
    private final Box bottomBox = Box.createHorizontalBox();

    {
        this.initComponent();
        super.init();
    }

    @Override
    public JComponent createPanel() {
        return rootBox;
    }

    @Override
    public String title() {
        return "Yaml文件处理";
    }

    private void initComponent() {
        //创建编辑器
        etf.setPlaceholder("请输入需要校验的Yaml格式内容...");
        //检索按钮监听点击事件
        checkFileBtn.addActionListener(event -> {
            var yamlText = etf.getText();
            if (StrUtil.isEmpty(yamlText)) {
                MessageUtils.showErrorMessage("内容不能为空");
                return;
            }
            try {
                new Yaml().load(yamlText);
                MessageUtils.showErrorMessage("格式校验通过");
            } catch (Exception e) {
                MessageUtils.showErrorMessage("格式校验异常:" + e.getMessage());
            }
        });
        getWindow().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                etf.setText("");
            }
        });
        //装填文本框和底部按钮
        topBox.add(etf);
        bottomBox.add(checkFileBtn);

        //追加到根Box
        rootBox.add(topBox);
        rootBox.add(bottomBox);
    }
}
