package com.lzy.common.tool;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * desc: {@link ToolDate} <br/>
 * time: 2018/8/2 15:40 <br/>
 * author: 义仍 <br/>
 * since V 1.0 <br/>
 */
public class ToolDateTest {
    private Date date20180802_16_33_44;
    private Date date20180801_16_33_44;
    private Date date20180801_00_00_00;
    private Date date20180801_00_00_01;
    private Date date20180801_23_59_59;
    private Date date20180801_11_12_24;
    private Date date20180731_23_59_59;

    @Before
    public void setUp() {
        date20180802_16_33_44 = new Date(1533198824 * 1000L);// 2018/8/2 16:33:44
        date20180801_16_33_44 = new Date(1533112424 * 1000L);// 2018/8/1 16:33:44
        date20180801_00_00_00 = new Date(1533052800 * 1000L);// 2018/8/1 00:00:00
        date20180801_00_00_01 = new Date(1533052801 * 1000L);// 2018/8/1 00:00:01
        date20180801_23_59_59 = new Date(1533139199 * 1000L);// 2018/8/1 23:59:59
        date20180801_11_12_24 = new Date(1533093144 * 1000L);// 2018/8/1 11:12:24
        date20180731_23_59_59 = new Date(1533052799 * 1000L);// 2018/7/31 23:59:59
    }

    /**
     * {@link ToolDate#getFormatDate(long, String)} 单元测试
     */
    @Test
    public void testGetFormatDateWithTimeStamp() {
        // yyyy/MM/dd 日期格式测试
        assertEquals("2018/08/02", ToolDate.getFormatDate(date20180802_16_33_44.getTime() / 1000, "yyyy/MM/dd"));
        assertNotEquals("2018/8/2", ToolDate.getFormatDate(date20180802_16_33_44.getTime() / 1000, "yyyy/MM/dd"));
        assertNotEquals("2018/08/01", ToolDate.getFormatDate(date20180802_16_33_44.getTime() / 1000, "yyyy/MM/dd"));

        // HH:mm 日期格式测试
        assertEquals("16:33", ToolDate.getFormatDate(date20180802_16_33_44.getTime() / 1000, "HH:mm"));

        // yyyy-MM-dd HH:mm 日期格式测试
        assertEquals("2018-08-02 16:33", ToolDate.getFormatDate(date20180802_16_33_44.getTime() / 1000, "yyyy-MM-dd HH:mm"));
    }

    /**
     * {@link ToolDate#getFormatDate(Date, String)} 单元测试
     */
    @Test
    public void testGetFormatDateWithDate() {
        // yyyy/MM/dd 日期格式测试
        assertEquals("2018/08/02", ToolDate.getFormatDate(date20180802_16_33_44, "yyyy/MM/dd"));
        assertNotEquals("2018/8/2", ToolDate.getFormatDate(date20180802_16_33_44, "yyyy/MM/dd"));
        assertNotEquals("2018/08/01", ToolDate.getFormatDate(date20180802_16_33_44, "yyyy/MM/dd"));

        // HH:mm 日期格式测试
        assertEquals("16:33", ToolDate.getFormatDate(date20180802_16_33_44, "HH:mm"));

        // yyyy-MM-dd HH:mm 日期格式测试
        assertEquals("2018-08-02 16:33", ToolDate.getFormatDate(date20180802_16_33_44, "yyyy-MM-dd HH:mm"));
    }

    /**
     * {@link ToolDate#getFormatDate(Date, String, Locale)} 单元测试
     * 阿语locale设置参考StackOverflow上的这个链接{https://stackoverflow.com/questions/29154887/setting-arabic-numbering-system-locale-doesnt-show-arabic-numbers/29155743#29155743}
     */
    @Test
    public void testGetFormatDateWithLocale() {
        // 阿语locale下不同日期格式测试
        Locale arabicLocale = new Locale.Builder().setLanguage("ar").setRegion("SA")
                .setExtension(Locale.UNICODE_LOCALE_EXTENSION, "nu-arab").build();
        assertEquals("٢٠١٨/٠٨/٠٢ ١٦:٣٣", ToolDate.getFormatDate(date20180802_16_33_44, "yyyy/MM/dd HH:mm", arabicLocale));
        assertEquals("٢٠١٨-٠٨-٠٢", ToolDate.getFormatDate(date20180802_16_33_44, "yyyy-MM-dd", arabicLocale));
        assertEquals("١٦:٣٣", ToolDate.getFormatDate(date20180802_16_33_44, "HH:mm", arabicLocale));

        // 英语locale下不同日期格式测试
        Locale usLocale = Locale.US;
        assertEquals("2018/08/02 16:33", ToolDate.getFormatDate(date20180802_16_33_44, "yyyy/MM/dd HH:mm", usLocale));
        assertEquals("2018-08-02", ToolDate.getFormatDate(date20180802_16_33_44, "yyyy-MM-dd", usLocale));
        assertEquals("16:33", ToolDate.getFormatDate(date20180802_16_33_44, "HH:mm", usLocale));
    }

    /**
     * {@link ToolDate#getHourMinSecond(long)} 单元测试
     */
    @Test
    public void testGetHourMinSecond() {
        // 通过秒数获取时、分、秒测试
        assertArrayEquals(new int[]{1, 2, 10}, ToolDate.getHourMinSecond(3730));
        assertArrayEquals(new int[]{34, 9, 0}, ToolDate.getHourMinSecond(122940));
    }

    /**
     * {@link ToolDate#getDayHourMinSecond(long)} 单元测试
     */
    @Test
    public void testGetDayHourMinSecond() {
        // 通过秒数获取日、时、分、秒测试
        assertArrayEquals(new int[]{2, 3, 4, 5}, ToolDate.getDayHourMinSecond(183845));
        assertArrayEquals(new int[]{1, 0, 6, 0}, ToolDate.getDayHourMinSecond(86760));
    }

    /**
     * {@link ToolDate#isSameDay(long, long)} 单元测试
     */
    @Test
    public void testIsSameDate() {
        // 非同一天测试
        assertEquals(false, ToolDate.isSameDay(date20180802_16_33_44.getTime(), date20180801_16_33_44.getTime()));

        // 同一天测试
        assertEquals(true, ToolDate.isSameDay(date20180801_00_00_01.getTime(), date20180801_11_12_24.getTime()));

        // 同一天边界情况测试
        assertEquals(true, ToolDate.isSameDay(date20180801_00_00_00.getTime(), date20180801_00_00_01.getTime()));
        assertEquals(true, ToolDate.isSameDay(date20180801_00_00_00.getTime(), date20180801_23_59_59.getTime()));

        // 非同一天边界情况测试
        assertEquals(false, ToolDate.isSameDay(date20180801_00_00_00.getTime(), date20180731_23_59_59.getTime()));
    }

}
