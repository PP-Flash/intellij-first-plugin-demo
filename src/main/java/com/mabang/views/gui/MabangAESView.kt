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
        const val PKCS7Padding = "PKCS7Padding"

        //马帮AES解密信息
        val AesKey = "A453AC4877D0F1A96A76B1CF13E76D63".toByteArray()

        val aesIv = "c558Gq0YQK2QUlMc".toByteArray()

        //马帮MdcAES解密信息
        val mdcAesKey = "B9zVorJ32GqNAZHw".toByteArray()

        val mdcAesIv = "1234567890123456".toByteArray()
    }

    //最外层Panel面板
    private lateinit var aesMainPanel: JPanel

    //解密按钮
    private lateinit var decryptBtn: JButton
    private lateinit var cryptBtn: JButton
    private lateinit var clearBtn: JButton

    //需要解密的内容
    private lateinit var decryptTextArea: JTextArea

    //解密结果展示
    private lateinit var aesResultArea: JTextArea
    private lateinit var mdcAesResultArea: JTextArea

    init {
        super.init()
        //加解密按钮监听
        decryptBtn.addActionListener { this.decryptContent() }
        cryptBtn.addActionListener { this.cryptContent() }
        //清空文本
        clearBtn.addActionListener {
            decryptTextArea.text = ""
            aesResultArea.text = ""
            mdcAesResultArea.text = ""
        }
    }

    /**
     * 解密内容
     */
    private fun decryptContent() {
        //解密指定内容
        try {
            val aes = AES(Mode.CBC, Padding.PKCS5Padding, AesKey)
            aes.setIv(aesIv)
            aesResultArea.text = aes.decryptStr(decryptTextArea.text)
        } catch (e: Exception) {
            aesResultArea.text = "解密失败,不符合加密规则"
        }

        try {
            val mdcAes = AES(Mode.CBC.toString(), PKCS7Padding, mdcAesKey)
            mdcAes.setIv(mdcAesIv)
            mdcAesResultArea.text = mdcAes.decryptStr(decryptTextArea.text)
        } catch (e: Exception) {
            mdcAesResultArea.text = "解密失败,不符合加密规则"
        }
    }

    /**
     * 加密内容
     */
    private fun cryptContent() {
        try {
            val aes = AES(Mode.CBC, Padding.PKCS5Padding, AesKey)
            aes.setIv(aesIv)
            aesResultArea.text = aes.encryptBase64(decryptTextArea.text)
        } catch (e: Exception) {
            aesResultArea.text = "加密失败:${e.message}"
        }

        try {
            val mdcAes = AES(Mode.CBC.toString(), PKCS7Padding, mdcAesKey)
            mdcAes.setIv(mdcAesIv)
            mdcAesResultArea.text = mdcAes.encryptBase64(decryptTextArea.text)
        } catch (e: Exception) {
            mdcAesResultArea.text = "加密失败:${e.message}"
        }
    }

    override fun createPanel(): JComponent {
        return aesMainPanel
    }
}