package com.lzy.common.tool;

import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * desc: {@link ToolNumber} 单元测试 <br/>
 * time: 2018/8/23 下午5:29 <br/>
 * author: Logan <br/>
 * since V 1。2 <br/>
 */
public class ToolNumberTest {

    private final String nullStr = null;
    private final String emptyStr = "";
    private final String numberBeginStr = "1abc";
    private final String numberEndStr = "abc2";
    private final String numberBeginEndStr = "1abc2";
    private final String numberMiddleStr = "ab23c";
    private final String numberSBC = "１２３４５６７８９０";
    private final String dotFlag = ".";
    private final String negativeFlag = "-";
    private final String numberDotStr = "12.";
    private final String numberDoubleStr = "12345.0389";
    private final String numberNegativeStr = "-012345";
    private final String numberAllStr = "123456789";

    @Test
    public void isDigit() {
        /* null */
        assertEquals(false, ToolNumber.isDigit(nullStr));
        /* empty String */
        assertEquals(false, ToolNumber.isDigit(emptyStr));
        /* 数字开头 */
        assertEquals(false, ToolNumber.isDigit(numberBeginStr));
        /* 数字结尾 */
        assertEquals(false, ToolNumber.isDigit(numberEndStr));
        /* 中间有数字*/
        assertEquals(false, ToolNumber.isDigit(numberMiddleStr));
        /* 全角数字*/
        assertEquals(false, ToolNumber.isDigit(numberSBC));
        /* 小数点 */
        assertEquals(false, ToolNumber.isDigit(dotFlag));
        /* 负号 */
        assertEquals(false, ToolNumber.isDigit(negativeFlag));
        /* 数字+小数点 */
        assertEquals(false, ToolNumber.isDigit(numberDotStr));
        /* 浮点型数字 */
        assertEquals(false, ToolNumber.isDigit(numberDoubleStr));
        /* 负数值 */
        assertEquals(false, ToolNumber.isDigit(numberNegativeStr));
        /* 全数字值 */
        assertEquals(true, ToolNumber.isDigit(numberAllStr));
        /* Long最大值 */
        assertEquals(true, ToolNumber.isDigit(Long.MAX_VALUE + ""));
    }

    /**
     * {@link ToolNumber#isInteger(String)} 单元测试
     */
    @Test
    public void isInteger() {
        /* null */
        assertEquals(false, ToolNumber.isInteger(nullStr));
        /* empty String */
        assertEquals(false, ToolNumber.isInteger(emptyStr));
        /* 数字开头 */
        assertEquals(false, ToolNumber.isInteger(numberBeginStr));
        /* 数字结尾 */
        assertEquals(false, ToolNumber.isInteger(numberEndStr));
        /* 前后有数字 */
        assertEquals(false, ToolNumber.isInteger(numberBeginEndStr));
        /* 中间有数字*/
        assertEquals(false, ToolNumber.isInteger(numberMiddleStr));
        /* 全角数字*/
        assertEquals(false, ToolNumber.isInteger(numberSBC));
        /* 小数点 */
        assertEquals(false, ToolNumber.isInteger(dotFlag));
        /* 负号 */
        assertEquals(false, ToolNumber.isInteger(negativeFlag));
        /* 数字+小数点 */
        assertEquals(false, ToolNumber.isInteger(numberDotStr));
        /* 浮点型数字 */
        assertEquals(false, ToolNumber.isInteger(numberDoubleStr));
        /* 负数值 */
        assertEquals(true, ToolNumber.isInteger(numberNegativeStr));
        /* 全数字值 */
        assertEquals(true, ToolNumber.isInteger(numberAllStr));
    }

    /**
     * {@link ToolNumber#isNumeric(String)} 单元测试
     */
    @Test
    public void isNumericExt() {
        /* null */
        assertEquals(false, ToolNumber.isNumeric(nullStr));
        /* empty String */
        assertEquals(false, ToolNumber.isNumeric(emptyStr));
        /* 数字开头的字符串 */
        assertEquals(false, ToolNumber.isNumeric(numberBeginStr));
        /* 数字结尾的字符串 */
        assertEquals(false, ToolNumber.isNumeric(numberEndStr));
        /* 前后有数字的字符串 */
        assertEquals(false, ToolNumber.isNumeric(numberBeginEndStr));
        /* 中间有数字的字符串 */
        assertEquals(false, ToolNumber.isNumeric(numberMiddleStr));
        /* 全角数字字符串 */
        assertEquals(false, ToolNumber.isNumeric(numberSBC));
        /* 小数点字符串 */
        assertEquals(false, ToolNumber.isNumeric(dotFlag));
        /* 负号字符串 */
        assertEquals(false, ToolNumber.isNumeric(negativeFlag));
        /* 数字+小数点 */
        assertEquals(false, ToolNumber.isNumeric(numberDotStr));
        /* 浮点型数字字符串 */
        assertEquals(true, ToolNumber.isNumeric(numberDoubleStr));
        /* 负数值字符串 */
        assertEquals(true, ToolNumber.isNumeric(numberNegativeStr));
        /* 全数字值字符串 */
        assertEquals(true, ToolNumber.isNumeric(numberAllStr));
    }

    /**
     * {@link ToolNumber#toInt(Object)} 单元测试
     */
    @Test
    public void toInt() {
        /* Integer 类型*/
        {
            assertEquals(0, ToolNumber.toInt(null));
            assertEquals(-1, ToolNumber.toInt(-1));
            assertEquals(0, ToolNumber.toInt(0));
            assertEquals(1, ToolNumber.toInt(1));
            assertEquals(-1, ToolNumber.toInt(-1.13));
            assertEquals(-1, ToolNumber.toInt(-1.99));
        }

        /* Number 类型*/
        {
            Number numberNull = null;
            Number numberNegativeValue = -1;
            Number numberZero = 0;
            Number numberValue = 1;

            assertEquals(0, ToolNumber.toInt(numberNull));
            assertEquals(-1, ToolNumber.toInt(numberNegativeValue));
            assertEquals(0, ToolNumber.toInt(numberZero));
            assertEquals(1, ToolNumber.toInt(numberValue));
        }

        /* String 类型*/
        {
            assertEquals(0, ToolNumber.toInt(null));
            assertEquals(0, ToolNumber.toInt(""));
            /* 全角数字 */
            assertEquals(0, ToolNumber.toInt("１２３４"));
            assertEquals(0, ToolNumber.toInt(numberBeginStr));
            assertEquals(0, ToolNumber.toInt(numberEndStr));
            assertEquals(11, ToolNumber.toInt("11"));
            /* 小数 */
            assertEquals(11, ToolNumber.toInt("11.19"));
            /* 小数 */
            assertEquals(11, ToolNumber.toInt("11.99"));
            /* 负数小数 */
            assertEquals(-1, ToolNumber.toInt("-1.13"));
            /* 负数小数 */
            assertEquals(-1, ToolNumber.toInt("-1.99"));
        }
    }

    /**
     * {@link ToolNumber#toLong(Object)} 单元测试
     */
    @Test
    public void toLong() {
        /* Long 类型*/
        {
            assertEquals(0, ToolNumber.toLong(null));
            assertEquals(-1, ToolNumber.toLong(-1));
            assertEquals(0, ToolNumber.toLong(0));
            assertEquals(1, ToolNumber.toLong(1));
            assertEquals(-1, ToolNumber.toLong(-1.13));
            assertEquals(-1, ToolNumber.toLong(-1.99));
        }

        /* Number 类型*/
        {
            Number numberNull = null;
            Number numberNegativeValue = -1;
            Number numberZero = 0;
            Number numberValue = 1;

            assertEquals(0, ToolNumber.toLong(numberNull));
            assertEquals(-1, ToolNumber.toLong(numberNegativeValue));
            assertEquals(0, ToolNumber.toLong(numberZero));
            assertEquals(1, ToolNumber.toLong(numberValue));
        }

        /* String 类型*/
        {
            assertEquals(0, ToolNumber.toLong(null));
            assertEquals(0, ToolNumber.toLong(""));
            /* 全角数字 */
            assertEquals(0, ToolNumber.toLong("１２３４"));
            assertEquals(0, ToolNumber.toLong(numberBeginStr));
            assertEquals(0, ToolNumber.toLong(numberEndStr));
            assertEquals(23, ToolNumber.toLong("23"));
            /* 小数 */
            assertEquals(23, ToolNumber.toLong("23.19"));
            /* 小数 */
            assertEquals(23, ToolNumber.toLong("23.99"));
            /* 负数小数 */
            assertEquals(-3, ToolNumber.toInt("-3.33"));
            /* 负数小数 */
            assertEquals(-3, ToolNumber.toInt("-3.99"));
        }
    }

    /**
     * {@link ToolNumber#toShort(Object)} 单元测试
     */
    @Test
    public void toShort() {
        /* Short 类型*/
        {
            assertEquals(0, ToolNumber.toShort(null));
            assertEquals(-1, ToolNumber.toShort(-1));
            assertEquals(0, ToolNumber.toShort(0));
            assertEquals(1, ToolNumber.toShort(1));
            assertEquals(-1, ToolNumber.toShort(-1.13));
            assertEquals(-1, ToolNumber.toShort(-1.99));
            assertEquals(-1, ToolNumber.toShort(-1.99));
        }

        /* Number 类型*/
        {
            Number numberNull = null;
            Number numberNegativeValue = -1;
            Number numberZero = 0;
            Number numberValue = 1;

            assertEquals(0, ToolNumber.toShort(numberNull));
            assertEquals(-1, ToolNumber.toShort(numberNegativeValue));
            assertEquals(0, ToolNumber.toShort(numberZero));
            assertEquals(1, ToolNumber.toShort(numberValue));
        }

        /* String 类型*/
        {
            assertEquals(0, ToolNumber.toShort(null));
            assertEquals(0, ToolNumber.toShort(""));
            /* 全角数字 */
            assertEquals(0, ToolNumber.toShort("１２３４"));
            assertEquals(0, ToolNumber.toShort(numberBeginStr));
            assertEquals(0, ToolNumber.toShort(numberEndStr));
            assertEquals(55, ToolNumber.toShort("55"));
            /* 小数 */
            assertEquals(55, ToolNumber.toShort("55.19"));
            /* 小数 */
            assertEquals(55, ToolNumber.toShort("55.99"));
            /* 负数小数 */
            assertEquals(-5, ToolNumber.toShort("-5.33"));
            /* 负数小数 */
            assertEquals(-5, ToolNumber.toShort("-5.99"));
        }
    }

    /**
     * {@link ToolNumber#toDouble(Object)} 单元测试
     */
    @Test
    public void toDouble() {
        /* Double 类型*/
        {
            assertEquals(0, ToolNumber.toDouble(null), 0);
            assertEquals(-1.0, ToolNumber.toDouble(-1), 0);
            assertEquals(0, ToolNumber.toDouble(0.0), 0);
            assertEquals(1.0, ToolNumber.toDouble(1.0), 0);
            assertEquals(-1.13, ToolNumber.toDouble(-1.13), 0);
            assertEquals(-1.99, ToolNumber.toDouble(-1.99), 0);
        }

        /* Number 类型 */
        {
            Number numberNull = null;
            Number numberNegativeValue = -1.0;
            Number numberZero = 0.0;
            Number numberValue = 1;

            assertEquals(0, ToolNumber.toDouble(numberNull), 0);
            assertEquals(-1, ToolNumber.toDouble(numberNegativeValue), 0);
            assertEquals(0.0, ToolNumber.toDouble(numberZero), 0);
            assertEquals(1.0, ToolNumber.toDouble(numberValue), 0);

            /* 除法未截取的情况：0.333333333333333 */
            assertEquals(0.33, ToolNumber.toDouble(1 / 3.0), 0.01);
        }

        /* String 类型*/
        {
            assertEquals(0.0, ToolNumber.toDouble(null), 0);
            assertEquals(0, ToolNumber.toDouble(""), 0);
            /* 全角数字 */
            assertEquals(0.0, ToolNumber.toDouble("１２３４"), 0);
            assertEquals(0, ToolNumber.toDouble(numberBeginStr), 0);
            assertEquals(0.0, ToolNumber.toDouble(numberEndStr), 0);
            assertEquals(11.0, ToolNumber.toDouble("11"), 0);
            /* 小数 */
            assertEquals(11.19, ToolNumber.toDouble("11.19"), 0);
            /* 小数 */
            assertEquals(11.99, ToolNumber.toDouble("11.99"), 0);
            /* 负数小数 */
            assertEquals(-1.13, ToolNumber.toDouble("-1.13"), 0);
            /* 负数小数 */
            assertEquals(-1.99, ToolNumber.toDouble("-1.99"), 0);
        }

    }

    /**
     * /**
     * {@link ToolNumber#toDouble(DecimalFormat, Object)} 单元测试
     */
    @Test
    public void toDoubleWithFormater() {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.CHINA);

        if (format instanceof DecimalFormat) {
            /* 数字格式化器 */
            DecimalFormat dcmFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.CHINA);
            dcmFormat.setRoundingMode(RoundingMode.HALF_UP); //四舍五入
            dcmFormat.applyPattern(".##"); //截取两位小数

            /* 常规性当权测试 */
            assertEquals(0.0, ToolNumber.toDouble(dcmFormat, null), 0);
            assertEquals(-0.1, ToolNumber.toDouble(dcmFormat, -0.1), 0);
            assertEquals(0.0, ToolNumber.toDouble(dcmFormat, 0.0), 0);
            assertEquals(0.0, ToolNumber.toDouble(dcmFormat, "１２３４"), 0);
            assertEquals(0.0, ToolNumber.toDouble(dcmFormat, numberBeginStr), 0);

            /* 截取多余的位数：0.333333333333333 -> 0.33 */
            assertEquals(0.33, ToolNumber.toDouble(dcmFormat, 1 / 3.0), 0);
            /* 不做任何处理 */
            assertEquals(0.45, ToolNumber.toDouble(dcmFormat, 0.45), 0);
            /* 四舍五入 */
            assertEquals(0.46, ToolNumber.toDouble(dcmFormat, 0.455), 0);
            /* 不能四舍五入 */
            assertEquals(0.45, ToolNumber.toDouble(dcmFormat, 0.4545), 0);
            assertEquals(0.45, ToolNumber.toDouble(dcmFormat, 0.4549999999), 0);
        } else {
            assertEquals(true, false);
        }
    }

    /**
     * {@link ToolNumber#getRandom1ToX(int)} 单元测试
     */
    @Test
    public void getRandomInt() {
        int x = 1;
        int count = 100000;

        for (int i = x; i < count; i++) {
            int random = ToolNumber.getRandom1ToX(x);

            if (1 <= random && random <= x) {
                assertEquals(true, true);
            } else {
                assertEquals(true, false);
            }
        }
    }

}