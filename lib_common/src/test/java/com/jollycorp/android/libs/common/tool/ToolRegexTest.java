package com.lzy.common.tool;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * desc: 正则工具 测试类 {@link ToolRegex} <br/>
 * time: 2018/8/29 <br/>
 * author 杨斌才 <br/>
 * since V 1.2 <br/>
 */
public class ToolRegexTest {



    /**
     * 电话号码正则表达式= (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)
     */
    private static final String REGEX_PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";

    /**
     * 手机号码正则表达式=^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$
     */
    private static final String REGEX_MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";

    /**
     * Integer正则表达式 ^-?(([1-9]\d*$)|0)
     */
    private static final String REGEX_INTEGER = "^-?(([1-9]\\d*$)|0)";

    /**
     * 正整数正则表达式 >=0 ^[1-9]\d*|0$
     */
    private static final String REGEX_INTEGER_NEGATIVE = "^[1-9]\\d*|0$";

    /**
     * 负整数正则表达式 <=0 ^-[1-9]\d*|0$
     */
    private static final String REGEX_INTEGER_POSITIVE = "^-[1-9]\\d*|0$";

    /**
     * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
     */
    private static final String REGEX_DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";

    /**
     * 正Double正则表达式 >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$
     */
    private static final String REGEX_DOUBLE_NEGATIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";

    /**
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁
     */
    private static final String REGEX_AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";

    /**
     * 邮编正则表达式  [0-9]\d{5}(?!\d) 国内6位邮编
     */
    private static final String REGEX_CODE = "[0-9]\\d{5}(?!\\d)";

    /**
     * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$
     */
    private static final String REGEX_STR_ENG_NUM = "^[A-Za-z0-9]+";

    /**
     * 匹配由26个英文字母组成的字符串  ^[A-Za-z]+$
     */
    private static final String REGEX_STR_ENG = "^[A-Za-z]+$";



    /**
     * {@link ToolRegex#isEmail(String)}
     */
    @Test
    public void testEmail() {

        // 空字符
        String email1 = "";

        // 2位数字
        String email2 = "12@qq.com";

        // . + 数字
        String email3 = "hhhhhh.1_2@qq.com";

        // 中文符号
        String email4 = "。12@qq.com";

        // 中文
        String email5 = "邮箱12@qq.com";

        // 无 @ 符号
        String email6 = "1234qq.com";

        // 无 .com
        String email7 = "a1234@qq_com";

        // @ 前含非'.' '_' 符号
        String email8 = "a_123!$4@qq.com";

        // @ 前字符较多
        String email9 = "a_123ddddddddddddd4@qq.ccom";

        // 域名过长
        String email10 = "a_123ddddddddddddd4@qq.cccccom";
        String email11 = "hjkjhk@645654.2121-6878.com.wcn";
        String email12 = "xxxxxxxxx@wwew-163.com.cn";
        String email13 = "xxxxxxx@163.com";

        assertFalse(ToolRegex.isEmail(email1));
        assertTrue(ToolRegex.isEmail(email2));
        assertTrue(ToolRegex.isEmail(email3));
        assertFalse(ToolRegex.isEmail(email4));
        assertFalse(ToolRegex.isEmail(email5));
        assertFalse(ToolRegex.isEmail(email6));
        assertFalse(ToolRegex.isEmail(email7));
        assertFalse(ToolRegex.isEmail(email8));
        assertTrue(ToolRegex.isEmail(email9));
        assertFalse(ToolRegex.isEmail(email10));
        assertTrue(ToolRegex.isEmail(email11));
        assertTrue(ToolRegex.isEmail(email12));
        assertTrue(ToolRegex.isEmail(email13));
    }


    /**
     * {@link ToolRegex#isMatches(String, String)}
     */
    @Test
    public void testRegexMatches() {

        // 座机
        String phone = "121-4345254235";

        // 手机号
        String mobile = "13345678911";

        // 整数
        String integer = "-1342325";

        // +整数
        String integer_negative = "1342325";

        // -整数
        String integer_positive = "-1342325";

        // double型数字
        String doubleNum = "3425.245";

        // +double型数字
        String double_negative = "3425.245";

        // 年龄
        String age = "200";

        // 国内邮政编码
        String emailCode = "31180012";

        // 英文字母+数字
        String str_eng_num = "sadfa2435215";

        // 英文字母
        String str_eng = "adfasgag";

        assertTrue(ToolRegex.isMatches(phone, REGEX_PHONE));
        assertTrue(ToolRegex.isMatches(mobile, REGEX_MOBILE));
        assertTrue(ToolRegex.isMatches(integer, REGEX_INTEGER));
        assertTrue(ToolRegex.isMatches(integer_negative, REGEX_INTEGER_NEGATIVE));
        assertTrue(ToolRegex.isMatches(integer_positive, REGEX_INTEGER_POSITIVE));
        assertTrue(ToolRegex.isMatches(doubleNum, REGEX_DOUBLE));
        assertTrue(ToolRegex.isMatches(double_negative, REGEX_DOUBLE_NEGATIVE));
        assertFalse(ToolRegex.isMatches(age, REGEX_AGE));
        assertFalse(ToolRegex.isMatches(emailCode, REGEX_CODE));
        assertTrue(ToolRegex.isMatches(str_eng_num, REGEX_STR_ENG_NUM));
        assertTrue(ToolRegex.isMatches(str_eng, REGEX_STR_ENG));

    }


    @Test
    public void testReplaceAll() {
        String ss1 = "abdsjghagafgahsdh";
        String regex1 = "a";
        String replace1 = "A";
        assertEquals("AbdsjghAgAfgAhsdh", ToolRegex.getReplaceAll(ss1, regex1, replace1));

        String ss2 = "aAbAz";
        String regex2 = "[a-z]";
        String replace2 = "中";
        assertEquals("中A中A中", ToolRegex.getReplaceAll(ss2, regex2, replace2));

        String ss3 = "aAbAz";
        String regex3 = "[0-9]";
        String replace3 = "中";
        assertEquals("aAbAz", ToolRegex.getReplaceAll(ss3, regex3, replace3));

        String ss4 = "aAbAz";
        String regex4 = "[a-z]{2}";
        String replace4 = "中";
        assertEquals("aAbAz", ToolRegex.getReplaceAll(ss4, regex4, replace4));

        String ss5 = "ag!#ahg1a;jrgoajg#$ajasjsjd";
        String regex5 = "[a-z]{2}";
        String replace5 = "中";
        assertEquals("中!#中g1a;中中中g#$中中中中", ToolRegex.getReplaceAll(ss5, regex5, replace5));
    }
}