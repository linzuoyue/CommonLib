package com.lzy.common.tool;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.lzy.common.consts.LibCommonConst.CHARSET_UTF_8;


/**
 * desc: 编码工具类 <br/>
 * author: 尾生<br/>
 * time: 2018/8/16 下午4:05<br/>
 * since V 1.2 <br/>
 */
public interface ToolEncode {

    /**
     * 使用URL编码 UTF-8
     *
     * @param url url编码前值
     * @return 编码后的值
     */
    @NonNull
    static String encodeUrl(@Nullable String url) {
        if (ToolText.isEmptyOrNull(url)) {
            return "";
        }
        try {
            url = URLEncoder.encode(url, CHARSET_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 使用URL解码 默认UTF-8
     *
     * @param url url解码前值
     * @return 解码后的值
     */
    @NonNull
    static String decodeUrl(@Nullable String url) {
        if (ToolText.isEmptyOrNull(url)) {
            return "";
        }
        try {
            url = URLDecoder.decode(url, CHARSET_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 获取Base64编码后的值
     * 使用{@link Base64#NO_WRAP}方式省略所有换行符
     *
     * @param value 编码前字符串
     * @return 编码后的值
     */
    @NonNull
    static String encodeBase64(@Nullable String value) {
        if (ToolText.isEmptyOrNull(value)) {
            return "";
        }

        try {
            value = new String(Base64.encode(value.getBytes(), Base64.NO_WRAP), CHARSET_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 使用Base64解码.
     * 使用{@link Base64#NO_WRAP}方式省略所有换行符
     *
     * @param value 解码后字符串
     * @return 解码后的值
     */
    @NonNull
    static String decodeBase64(@Nullable String value) {
        if (ToolText.isEmptyOrNull(value)) {
            return "";
        }

        try {
            value = new String(Base64.decode(value.getBytes(), Base64.NO_WRAP), CHARSET_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
