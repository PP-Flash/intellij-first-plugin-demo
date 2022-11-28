package com.github.nanbiango.views.gui

import cn.hutool.crypto.Mode
import cn.hutool.crypto.Padding
import cn.hutool.crypto.symmetric.AES
import com.github.nanbiango.views.base.CustomRootView
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextArea

/**
 * 马帮的AES解密
 *
 * @author wangchenglong
 * @since 2022-11-28
 */
class MabangAESView(viewTitle: String, width: Int, height: Int) : CustomRootView(viewTitle, width, height) {

    companion object {
        //马帮AES解密信息
        val aesKey = "A453AC4877D0F1A96A76B1CF13E76D63".toByteArray()
        val aesIv = "c558Gq0YQK2QUlMc".toByteArray()

        //马帮MdcAES解密信息
        val mdcAesKey = "B9zVorJ32GqNAZHw".toByteArray()
        val mdcAesIv = "1234567890123456".toByteArray()
    }

    //最外层Panel面板
    private lateinit var aesMainPanel: JPanel

    //解密按钮
    private lateinit var decryptBtn: JButton
    private lateinit var clearBtn: JButton

    //需要解密的内容
    private lateinit var decryptTextArea: JTextArea

    //解密结果展示
    private lateinit var decryptAesResultArea: JTextArea
    private lateinit var decryptMdcAesResultArea: JTextArea

    init {
        super.init()
        //解密按钮监听
        decryptBtn.addActionListener { this.decryptContent() }
        //清空文本
        clearBtn.addActionListener {
            decryptTextArea.text = ""
            decryptAesResultArea.text = ""
            decryptMdcAesResultArea.text = ""
        }
    }

    private fun decryptContent() {
        //解密指定内容
        try {
            val aes = AES(Mode.CBC, Padding.PKCS5Padding, aesKey)
            aes.setIv(aesIv)
            decryptAesResultArea.text = aes.decryptStr(decryptTextArea.text)
        } catch (e: Exception) {
            decryptAesResultArea.text = "解密失败,不符合加密规则"
        }

        try {
            val mdcAes = AES(Mode.CBC.toString(), "PKCS7Padding", mdcAesKey)
            mdcAes.setIv(mdcAesIv)
            decryptMdcAesResultArea.text = mdcAes.decryptStr(decryptTextArea.text)
        } catch (e: Exception) {
            decryptMdcAesResultArea.text = "解密失败,不符合加密规则"
        }
    }

    /**
     * 放入根Box
     */
    override fun createCenterPanel(): JComponent {
        super.setSize()
        return aesMainPanel
    }
}