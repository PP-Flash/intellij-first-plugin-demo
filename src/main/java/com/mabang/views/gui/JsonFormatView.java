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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Json数据处理视图
 */
public class JsonFormatView extends BaseDialogWrapper {

    public EditTextFieldPlus etfLeft = new EditTextFieldPlus(false, "请输入Json格式内容...");
    public EditTextFieldPlus etfRight = new EditTextFieldPlus();
    public JLabel transferLabel = new JLabel("  <=>  ");
    public JButton formatBtn = new JButton("增加转义符");
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
        formatBtn.addActionListener(e -> this.addEscapeTextHandle(etfLeft.getText()));
        //Json转义
        jsonEscapeBtn.addActionListener(e -> this.escapeJsonHandle(etfLeft.getText()));
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
    private void addEscapeTextHandle(String jsonText) {
        if (StrUtil.isEmpty(jsonText)) {
            MessageUtils.showErrorMessage("内容不能为空");
            return;
        }
        try {
            if (JSON.isValidArray(jsonText)) {
                jsonText = JSON.toJSONString(JSON.parseArray(jsonText).toJSONString());
            } else {
                jsonText = JSON.toJSONString(JSON.parseObject(jsonText).toJSONString());
            }
            etfRight.setText(jsonText);
        } catch (Exception e) {
            etfRight.setText("Json增加转义符异常:" + e.getMessage());
        }
    }

    private void escapeJsonHandle(String jsonText) {
        if (StrUtil.isEmpty(jsonText)) {
            MessageUtils.showErrorMessage("内容不能为空");
            return;
        }
        if (!JSON.isValidObject(jsonText) && !JSON.isValidArray(jsonText)) {
            MessageUtils.showErrorMessage("Json格式不合法");
            return;
        }
        etfRight.setText("Json内容格式化中...");
        try {
            var rootList = new ArrayList<Map<String, Object>>();
            if (JSON.isValidArray(jsonText)) {
                JSON.parseArray(jsonText, JSONObject.class).forEach(jsonObj -> rootList.add(this.escapeJson(((JSONObject) jsonObj).toJSONString())));
                etfRight.setText(JSON.toJSONString(rootList, JSONWriter.Feature.PrettyFormat));
            } else {
                etfRight.setText(JSON.toJSONString(this.escapeJson(jsonText), JSONWriter.Feature.PrettyFormat));
            }
        } catch (Exception e) {
            etfRight.setText("异常:" + e.getMessage());
        }
    }

    /**
     * 转义Json对象
     */
    private Map<String, Object> escapeJson(String jsonText) {
        var jsonMap = new HashMap<String, Object>();
        if (JSON.isValidObject(jsonText)) {
            try {
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
            } catch (Exception e) {
                return Collections.singletonMap("Json处理失败-" + e.getMessage(), jsonText);
            }
        }
        return jsonMap;
    }
}
