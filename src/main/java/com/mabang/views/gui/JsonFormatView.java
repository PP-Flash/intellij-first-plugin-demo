package com.mabang.views.gui;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.mabang.component.EditTextFieldPlus;
import com.mabang.utils.MessageUtils;
import com.mabang.views.base.BaseDialogWrapper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Json数据处理视图
 */
public class JsonFormatView extends BaseDialogWrapper {

    public EditTextFieldPlus etfText = new EditTextFieldPlus(false, "请输入Json格式内容...");
    public JButton formatBtn = new JButton("去除转义符");
    public JButton jsonEscapeBtn = new JButton("Json格式化");
    public JButton clearBtn = new JButton("清空文本");
    public Box topBox = Box.createHorizontalBox();
    public Box bottomBox = Box.createHorizontalBox();

    public JsonFormatView() {
        this.initComponent();
        super.init();
    }

    @Override
    public JComponent createPanel() {
        return rootBox;
    }

    @Override
    public String title() {
        return "Json数据处理";
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
        //格式化内容点击事件
        formatBtn.addActionListener(e -> this.formatJsonHandle(etfText.getText()));
        //Json转义
        jsonEscapeBtn.addActionListener(e -> this.escapeJsonHandle(etfText.getText()));
        //清空
        clearBtn.addActionListener(e -> {
            etfText.setText("");
        });
        getWindow().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                etfText.setText("");
            }
        });

        //装填文本框和底部按钮
        topBox.add(etfText);

        bottomBox.add(formatBtn);
        bottomBox.add(jsonEscapeBtn);
        bottomBox.add(clearBtn);
        //追加到根Box
        rootBox.add(topBox);
        rootBox.add(bottomBox);
    }

    /**
     * 格式化Json
     */
    private void formatJsonHandle(String jsonText) {
        if (StrUtil.isEmpty(jsonText)) {
            MessageUtils.showErrorMessage("内容不能为空");
            return;
        }
        etfText.setText(jsonText.replace("\\", ""));
    }

    private void escapeJsonHandle(String jsonText) {
        if (StrUtil.isEmpty(jsonText)) {
            MessageUtils.showErrorMessage("内容不能为空");
            return;
        }
        if (!JSON.isValid(jsonText)) {
            MessageUtils.showErrorMessage("Json格式不合法");
            return;
        }
        try {
            var rootList = new ArrayList<Map<String, Object>>();
            if (JSON.isValidArray(jsonText)) {
                JSON.parseArray(jsonText, JSONObject.class).forEach(jsonObj -> rootList.add(this.escapeJson(((JSONObject) jsonObj).toJSONString())));
                etfText.setText(JSON.toJSONString(rootList, JSONWriter.Feature.PrettyFormat));
            } else {
                etfText.setText(JSON.toJSONString(this.escapeJson(jsonText), JSONWriter.Feature.PrettyFormat));
            }
        } catch (Exception e) {
            MessageUtils.showErrorMessage("异常:" + e.getMessage());
        }
    }

    /**
     * 转义Json对象
     */
    private Map<String, Object> escapeJson(String jsonText) {
        var jsonMap = new HashMap<String, Object>();
        if (JSON.isValidObject(jsonText)) {
            JSON.parseObject(jsonText).forEach((key, value) -> {
                var v = value.toString();
                if (JSON.isValidObject(v)) {
                    jsonMap.put(key, this.escapeJson(v));
                } else if (JSON.isValidArray(v)) {
                    var list = new ArrayList<Map<String, Object>>();
                    JSONArray.parseArray(v)
                            .toList(JSONObject.class)
                            .forEach(json -> list.add(this.escapeJson(json.toJSONString())));
                    jsonMap.put(key, list);
                } else {
                    jsonMap.put(v, key);
                }
            });
        }
        return jsonMap;
    }
}
