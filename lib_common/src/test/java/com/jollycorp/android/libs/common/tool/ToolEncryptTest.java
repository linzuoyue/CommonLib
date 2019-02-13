package com.lzy.common.tool;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * desc: {@link ToolEncrypt} 单元测试 <br/>
 * author: 尾生<br/>
 * time: 2018/8/22 下午3:19<br/>
 * since V 1.2 <br/>
 */
public class ToolEncryptTest {
    /**
     * 加密前字符串
     */
    private String encryptBefore;
    /**
     * MD5加密后大写字符串
     */
    private String md5EncryptAfterUpCase;
    /**
     * MD5加密后小写写字符串
     */
    private String md5EncryptAfterLowCase;

    /**
     * SHA-256加密后小写字符串
     */
    private String shaEncryptAfterLowCase;
    /**
     * SHA-256加密后大写字符串
     */
    private String shaEncryptAfterUpCase;

    @Before
    public void setUp() {
        encryptBefore = "JollyChic_is_Great";
        md5EncryptAfterUpCase = "23A74DD30FF6AEDB38715F52D713C46B";
        md5EncryptAfterLowCase = "23a74dd30ff6aedb38715f52d713c46b";
        shaEncryptAfterLowCase = "9ae19f450f4f786920ccaa016a996500cfbba48ec05dfe736b8fe3c23b497c9f";
        shaEncryptAfterUpCase = "9AE19F450F4F786920CCAA016A996500CFBBA48EC05DFE736B8FE3C23B497C9F";
    }

    @Test
    public void testMd5Encrypt() {
        assertEquals(md5EncryptAfterUpCase, ToolEncrypt.getEncryptString(encryptBefore
                , ToolEncrypt.ENCRYPT_TYPE.ENC_TYPE_MD5).toUpperCase());
        assertEquals(md5EncryptAfterLowCase, ToolEncrypt.getEncryptString(encryptBefore
                , ToolEncrypt.ENCRYPT_TYPE.ENC_TYPE_MD5));
        assertEquals("", ToolEncrypt.getEncryptString(""
                , ToolEncrypt.ENCRYPT_TYPE.ENC_TYPE_MD5));
        assertEquals("", ToolEncrypt.getEncryptString(null
                , ToolEncrypt.ENCRYPT_TYPE.ENC_TYPE_MD5));
    }

    @Test
    public void testSha256Encrypt() {
        assertEquals(shaEncryptAfterUpCase, ToolEncrypt.getEncryptString(encryptBefore
                , ToolEncrypt.ENCRYPT_TYPE.ENC_TYPE_SHA256).toUpperCase());
        assertEquals(shaEncryptAfterLowCase, ToolEncrypt.getEncryptString(encryptBefore
                , ToolEncrypt.ENCRYPT_TYPE.ENC_TYPE_SHA256));
        assertEquals("", ToolEncrypt.getEncryptString(""
                , ToolEncrypt.ENCRYPT_TYPE.ENC_TYPE_SHA256));
        assertEquals("", ToolEncrypt.getEncryptString(null
                , ToolEncrypt.ENCRYPT_TYPE.ENC_TYPE_SHA256));
    }

}
