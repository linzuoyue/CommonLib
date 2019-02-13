package com.lzy.common.tool;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * desc: {@link ToolEncode} <br/>
 * author: 尾生<br/>
 * time: 2018/8/21 下午6:46<br/>
 * since V 1.2 <br/>
 */
@RunWith(AndroidJUnit4.class)
public class ToolEncodeTest {
    /**
     * 编码前字符串
     */
    private String encodeBefore;
    /**
     * urlEncode编码后字符串 通过在线工具生成 utf-8
     */
    private String urlEncodeAfter;

    /**
     * base64编码后字符串 通过在线工具生成 utf-8
     */
    private String base64EncodeAfter;

    @Before
    public void setUp() {
        encodeBefore = "http://h5app.jollychic.com/topicnew.html?edtionId=39706&lcid=test&title=testTitle";
        urlEncodeAfter = "http%3A%2F%2Fh5app.jollychic.com%2Ftopicnew.html%3FedtionId%3D39706%26lcid%3Dtest%26title%3DtestTitle";
        base64EncodeAfter = "aHR0cDovL2g1YXBwLmpvbGx5Y2hpYy5jb20vdG9waWNuZXcuaHRtbD9lZHRpb25JZD0zOTcwNiZsY2lkPXRlc3QmdGl0bGU9dGVzdFRpdGxl";
    }

    /**
     * {@link ToolEncode#encodeUrl(String)}} urlEncode测试
     */
    @Test
    public void testEncodeUrl() {
        assertEquals(urlEncodeAfter, ToolEncode.encodeUrl(encodeBefore));
        assertEquals("", ToolEncode.encodeUrl(null));
        assertEquals("", ToolEncode.encodeUrl(""));
        assertNotEquals(urlEncodeAfter, ToolEncode.encodeUrl(null));
        assertNotEquals(urlEncodeAfter, ToolEncode.encodeUrl(""));
    }

    /**
     * {@link ToolEncode#decodeUrl(String)} urlDecode测试
     */
    @Test
    public void testDecodeUrl() {
        assertEquals(encodeBefore, ToolEncode.decodeUrl(urlEncodeAfter));
        assertEquals(encodeBefore, ToolEncode.decodeUrl(ToolEncode.encodeUrl(encodeBefore)));
        assertEquals("", ToolEncode.decodeUrl(null));
        assertEquals("", ToolEncode.decodeUrl(""));
        assertNotEquals(encodeBefore, ToolEncode.decodeUrl(null));
        assertNotEquals(encodeBefore, ToolEncode.decodeUrl(""));
    }

    /**
     * {@link ToolEncode#encodeBase64(String)}base64Encode测试
     */
    @Test
    public void testEncodeBase64() {
        assertEquals(base64EncodeAfter, ToolEncode.encodeBase64(encodeBefore));
        assertEquals("", ToolEncode.encodeBase64(null));
        assertEquals("", ToolEncode.encodeBase64(""));
        assertNotEquals(base64EncodeAfter, ToolEncode.encodeBase64(null));
        assertNotEquals(base64EncodeAfter, ToolEncode.encodeBase64(""));
    }

    /**
     * {@link ToolEncode#decodeBase64(String)}  base64Decode测试
     */
    @Test
    public void testDecodeBase64() {
        assertEquals(encodeBefore, ToolEncode.decodeBase64(base64EncodeAfter));
        assertEquals(encodeBefore, ToolEncode.decodeBase64(ToolEncode.encodeBase64(encodeBefore)));
        assertEquals("", ToolEncode.decodeBase64(null));
        assertEquals("", ToolEncode.decodeBase64(""));
        assertNotEquals(encodeBefore, ToolEncode.decodeBase64(null));
        assertNotEquals(encodeBefore, ToolEncode.decodeBase64(""));
    }
}
