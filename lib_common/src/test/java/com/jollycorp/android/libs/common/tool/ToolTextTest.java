package com.lzy.common.tool;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * desc: {@link ToolText} 单元测试 <br/>
 * time: 2018/8/9 <br/>
 * author: 杨斌才 <br/>
 * since V 1.1 <br/>
 */
public class ToolTextTest {

    private static final String STR_EMPTY = "";
    private static final String STR_NULL = null;
    private static final String STR_SPACE = " ";
    private static final String STR_TABLE = "\t";
    private static final String STR_ENTER = "\r";
    private static final String STR_NORMAL1 = "adfafsgasg agag asfas  ";
    private static final String STR_NORMAL2 = "   saff  saf  af ";
    private static final String STR_NORMAL3 = "我是 text";
    private static final String STR_NORMAL4 = "虢\r恝";
    private static final String STR_NORMAL5 = "أهلاً بك";
    private static final String STR_TRIM1 = "شكرا للتسجيل في جولي شيك";
    private static final String STR_TRIM_BEFORE1 = "   شكرا للتسجيل في جولي شيك ";
    private static final String STR_TRIM_AFTER1 = "شكرا للتسجيل في جولي شيك";

    private static final String STR_TRIM2 = "abc def";
    private static final String STR_TRIM2_SIMILAR = "abcdef";
    private static final String STR_TRIM_BEFORE2 = " abc def";
    private static final String STR_TRIM_AFTER2 = "abc def";

    private static final String STR_TRIM3 = "abc def";
    private static final String STR_TRIM3_SIMILAR = "abcdef";
    private static final String STR_TRIM_BEFORE3 = "\r abc def \r";
    private static final String STR_TRIM_AFTER3 = "abc def";

    private static final String STR_HTTP = "http://git.jollycorp.com:8088/";
    private static final String STR_HTTP1 = "/http://git.jollycorp.com:8088/";
    private static final String STR_HTTP2 = "http://git.jollycorp.com:8088/android";


    /**
     * {@link ToolText#isEmptyOrNull(String)} 单元测试
     */
    @Test
    public void isEmptyOrNull() {
        // ""
        assertEquals(true, ToolText.isEmptyOrNull(STR_EMPTY));
        // null
        assertEquals(true, ToolText.isEmptyOrNull(STR_NULL));
        // table
        assertEquals(false, ToolText.isEmptyOrNull(STR_TABLE));
        // space
        assertEquals(false, ToolText.isEmptyOrNull(STR_SPACE));
        // enter
        assertEquals(false, ToolText.isEmptyOrNull(STR_ENTER));
        // "虢\r恝"
        assertEquals(false, ToolText.isEmptyOrNull(STR_NORMAL4));
        // "أهلاً بك"
        assertEquals(false, ToolText.isEmptyOrNull(STR_NORMAL5));
    }
    /**
     * {@link ToolText#isNotEmpty(String)} 单元测试
     */
    @Test
    public void isNotEmpty() {
        // ""
        assertEquals(false, ToolText.isNotEmpty(STR_EMPTY));
        // null
        assertEquals(false, ToolText.isNotEmpty(STR_NULL));
        // table
        assertEquals(true, ToolText.isNotEmpty(STR_TABLE));
        // space
        assertEquals(true, ToolText.isNotEmpty(STR_SPACE));
        // enter
        assertEquals(true, ToolText.isNotEmpty(STR_ENTER));
        // "虢\r恝"
        assertEquals(true, ToolText.isNotEmpty(STR_NORMAL4));
        // "أهلاً بك"
        assertEquals(true, ToolText.isNotEmpty(STR_NORMAL5));
    }

    /**
     * {@link ToolText#contains(String, String)} 单元测试
     */
    @Test
    public void contains() {
        //"شكرا للتسجيل في جولي شيك" 含 "شكرا للتسجيل في جولي شيك"
        assertEquals(true, ToolText.contains(STR_TRIM1, STR_TRIM_AFTER1));
        // "adfafsgasg agag asfas  " 含 " "
        assertEquals(true, ToolText.contains(STR_NORMAL1, STR_SPACE));
        // " " 不含 "adfafsgasg agag asfas  "
        assertEquals(false, ToolText.contains(STR_SPACE, STR_NORMAL1));
        // "虢\r恝" 含 "\r"
        assertEquals(true, ToolText.contains(STR_NORMAL4, STR_ENTER));
        // "虢\r恝" 不含 null
        assertEquals(false, ToolText.contains(STR_NORMAL4, STR_NULL));
        // "虢\r恝" 含 ""
        assertEquals(true, ToolText.contains(STR_NORMAL4, STR_EMPTY));
    }

    /**
     * {@link ToolText#trim(String)} 单元测试
     */
    @Test
    public void trim() {
        // "   شكرا للتسجيل في جولي شيك " 与 "شكرا للتسجيل في جولي شيك"
        assertNotEquals(STR_TRIM1, STR_TRIM_BEFORE1);
        assertNotEquals(STR_TRIM_AFTER1, STR_TRIM_BEFORE1);
        assertEquals(STR_TRIM1, ToolText.trim(STR_TRIM_BEFORE1));

        // " abcdef "
        assertNotEquals(STR_TRIM2, STR_TRIM2_SIMILAR);
        assertNotEquals(STR_TRIM_AFTER2, STR_TRIM_BEFORE2);
        assertEquals(STR_TRIM2, ToolText.trim(STR_TRIM_BEFORE2));
        assertNotEquals(STR_TRIM2_SIMILAR, ToolText.trim(STR_TRIM_BEFORE2));

        // "\r abc def \r"
        assertNotEquals(STR_TRIM3, STR_TRIM3_SIMILAR);
        assertNotEquals(STR_TRIM_AFTER3, STR_TRIM_BEFORE3);
        assertEquals(STR_TRIM3, ToolText.trim(STR_TRIM_BEFORE3));
        assertNotEquals(STR_TRIM3_SIMILAR, ToolText.trim(STR_TRIM_BEFORE3));

        // null or ""
        assertEquals(STR_NULL, ToolText.trim(STR_NULL));
        assertEquals(STR_EMPTY, ToolText.trim(STR_EMPTY));
    }

    /**
     * {@link ToolText#split(String, String)} 单元测试
     */
    @Test
    public void split() {
        // "http://git.jollycorp.com:8088/"
        String[] act = {"http:", "", "git.jollycorp.com:8088"};
        String[] split = ToolText.split(STR_HTTP, "/");
        assertArrayEquals(split, act);

        // "/http://git.jollycorp.com:8088/"
        String[] act2 = {"", "http:", "", "git.jollycorp.com:8088"};
        String[] split2 = ToolText.split(STR_HTTP1, "/");
        assertArrayEquals(split2, act2);

        // "/http://git.jollycorp.com:8088/"
        String[] act3 = {STR_HTTP1};
        String[] split3 = ToolText.split(STR_HTTP1, "///");
        assertArrayEquals(split3, act3);

        // "abcde"
        String[] act4 = {"a", "b", "c", "d", "e"};
        String[] split4 = ToolText.split("abcde", STR_EMPTY);
        assertArrayEquals(split4, act4);
        // "abcde"
        String[] act5 = {"abcde"};
        String[] split5 = ToolText.split("abcde", STR_NULL);
        assertArrayEquals(split5, act5);
    }

    /**
     * {@link ToolText#length(String)} 单元测试
     */
    @Test
    public void getLength() {
        // "   saff  saf  af ";
        assertEquals(17, ToolText.length(STR_NORMAL2));

        // "";
        assertEquals(0, ToolText.length(STR_EMPTY));

        // null;
        assertEquals(0, ToolText.length(STR_NULL));

        // "أهلاً بك"
        assertEquals(8, ToolText.length(STR_NORMAL5));

        // "\r"
        assertEquals(1, ToolText.length(STR_ENTER));
    }

    /**
     * {@link ToolText#countStr(String, String)} 单元测试
     */
    @Test
    public void countStr() {
        // "   saff  saf  af "
        assertEquals(3, ToolText.countStr(STR_NORMAL2, "a"));

        // "   saff  saf  afa"
        assertEquals(4, ToolText.countStr("   saff  saf  afa", "a"));

        // test same
        assertEquals(1, ToolText.countStr("aa", "aa"));

        // test empty
        assertEquals(0, ToolText.countStr("aa", ""));

        // test null
        assertEquals(0, ToolText.countStr("aa", null));

        // test null
        assertEquals(0, ToolText.countStr(null, "aa"));
    }

    /**
     * {@link ToolText#upperFirstLetter(String)} 单元测试
     */
    @Test
    public void upperFirstLetter() {
        // ""
        assertEquals("", ToolText.upperFirstLetter(""));

        // ""
        assertEquals(" ", ToolText.upperFirstLetter(" "));

        // null
        assertEquals(null, ToolText.upperFirstLetter(null));

        // "a"
        assertEquals("A", ToolText.upperFirstLetter(" a"));

        // " a"
        assertEquals("A", ToolText.upperFirstLetter(" a"));

        // "V"
        assertEquals("V", ToolText.upperFirstLetter("V"));

        // "abc"
        assertEquals("Abc", ToolText.upperFirstLetter("abc"));

        // "z"
        assertEquals("Z", ToolText.upperFirstLetter("z"));

        // "z"
        assertEquals("ZbsafDFADFAFF", ToolText.upperFirstLetter("zbsafDFADFAFF"));

        // ar
        assertEquals(STR_NORMAL5, ToolText.upperFirstLetter(STR_NORMAL5));

    }

    /**
     * {@link ToolText#lowerFirstLetter(String)} 单元测试
     */
    @Test
    public void lowerFirstLetter() {
        // ""
        assertEquals("", ToolText.lowerFirstLetter(""));

        // ""
        assertEquals("  ", ToolText.lowerFirstLetter("  "));

        // null
        assertEquals(null, ToolText.lowerFirstLetter(null));

        // "A"
        assertEquals("a", ToolText.lowerFirstLetter("A"));

        // " A"
        assertEquals("a", ToolText.lowerFirstLetter(" A"));

        // "v"
        assertEquals("v", ToolText.lowerFirstLetter("v"));

        // "ABc"
        assertEquals("aBc", ToolText.lowerFirstLetter("ABc"));

        // "Z"
        assertEquals("z", ToolText.lowerFirstLetter("Z"));

        // "ZbsafDFADFAFF"
        assertEquals("zbsafDFADFAFF", ToolText.lowerFirstLetter("ZbsafDFADFAFF"));

        // ar
        assertEquals(STR_NORMAL5, ToolText.lowerFirstLetter(STR_NORMAL5));

    }

    /**
     * {@link ToolText#equalsIgnoreCase(String, String)} 单元测试
     */
    @Test
    public void equalsIgnoreCase() {
        // null null
        assertTrue(ToolText.equalsIgnoreCase(null, null));

        // null null
        assertEquals(false, ToolText.equalsIgnoreCase(null, ""));

        // "" null
        assertEquals(false, ToolText.equalsIgnoreCase("", null));

        // "" ""
        assertEquals(true, ToolText.equalsIgnoreCase("", ""));

        // "ADFASF" "adfasf"
        assertEquals(true, ToolText.equalsIgnoreCase("ADFASF", "adfasf"));

        // "ADFASF" "  adfasf"
        assertEquals(false, ToolText.equalsIgnoreCase("ADFASF", "  adfasf"));

        // "ADFASF" "adfasf"
        assertEquals(false, ToolText.equalsIgnoreCase("ADFASF", "adf asf"));

        // "ADFASF"
        assertEquals(true, ToolText.equalsIgnoreCase("ADFASF", "ADFASF"));

        // "أهلاً بك"
        assertEquals(true, ToolText.equalsIgnoreCase(STR_NORMAL5, STR_NORMAL5));
    }
}