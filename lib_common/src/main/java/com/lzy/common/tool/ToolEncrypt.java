package com.lzy.common.tool;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.MessageDigest;

/**
 * desc: 加密工具类 <br/>
 * author: 尾生<br/>
 * time: 2018/8/22 下午2:20<br/>
 * since V 1.2 <br/>
 */
public interface ToolEncrypt {

    @StringDef
    @Retention(RetentionPolicy.SOURCE)
    @interface ENCRYPT_TYPE {
        /**
         * MD5加密方式
         */
        String ENC_TYPE_MD5 = "MD5";
        /**
         * SHA-256加密方式
         */
        String ENC_TYPE_SHA256 = "SHA-256";
    }

    /**
     * 获取加密后字符串
     * 默认使用SHA-256
     *
     * @param value   要加密的字符串
     * @param encName 加密类型 {@link ENCRYPT_TYPE}
     * @return 加密后字符串 16进制32位字符串(小写)
     */
    @NonNull
    static String getEncryptString(@Nullable String value, @ENCRYPT_TYPE String encName) {
        if (ToolText.isEmptyOrNull(value)) {
            return "";
        }

        String strDes;
        try {
            MessageDigest md = MessageDigest.getInstance(ToolText.isNotEmpty(encName)
                    ? encName : ENCRYPT_TYPE.ENC_TYPE_SHA256);
            md.update(value.getBytes());
            strDes = bytes2Hex(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            strDes = "";
        }
        return strDes;
    }

    /**
     * bytes转16进制字符串
     *
     * @param bts bytes数组
     * @return 16进制字符串 (小写)
     */
    @NonNull
    static String bytes2Hex(@NonNull byte[] bts) {
        StringBuilder sb = new StringBuilder();
        String tmp;

        for (byte bt : bts) {
            tmp = (Integer.toHexString(bt & 0xFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }

        return sb.toString();
    }

}
