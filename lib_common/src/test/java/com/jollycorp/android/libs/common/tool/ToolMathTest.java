package com.lzy.common.tool;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * desc: {@link ToolMath} 单元测试 <br/>
 * time: 2018/8/8 下午2:59 <br/>
 * author: 匡衡 <br/>
 * since V 1.2
 */
public class ToolMathTest {

    private final double deltaValue = 0d;
    private final double value1 = 10.3;
    private final double value2 = 10.33;

    /**
     * {@link ToolMath#doubleAdd(double, double)}单元测试
     */
    @Test
    public void doubleAdd() {
        //两个正数相加
        assertEquals(20.63, ToolMath.doubleAdd(value1, value2), deltaValue);
        //两个负数相加
        assertEquals(-20.63, ToolMath.doubleAdd(-value1, -value2), deltaValue);
        //一正一负相加
        assertEquals(0.03, ToolMath.doubleAdd(-value1, value2), deltaValue);
        //两个int类型的数据相加
        assertEquals(5, ToolMath.doubleAdd(3, 2), deltaValue);
        //一个int类型和一个double类型的数据相加
        assertEquals(13.3, ToolMath.doubleAdd(3, value1), deltaValue);
    }

    /**
     * {@link ToolMath#doubleSubtract(double, double)}单元测试
     */
    @Test
    public void doubleSubtract() {
        //被减数大于减数
        assertEquals(-0.03, ToolMath.doubleSubtract(value1, value2), deltaValue);
        //被减数小于减数
        assertEquals(0.03, ToolMath.doubleSubtract(value2, value1), deltaValue);
        //被减数等于减数
        assertEquals(0d, ToolMath.doubleSubtract(value1, value1), deltaValue);
        //两个int类型的数据相减
        assertEquals(2, ToolMath.doubleSubtract(5, 3), deltaValue);
        //一个int类型和一个double类型的数据相减
        assertEquals(7.3, ToolMath.doubleSubtract(value1, 3), deltaValue);
    }

    /**
     * {@link ToolMath#doubleMul(double, double)}单元测试
     */
    @Test
    public void doubleMul() {
        //两个正数相乘
        assertEquals(106.399, ToolMath.doubleMul(value1, value2), deltaValue);
        //一正一负相乘
        assertEquals(-106.399, ToolMath.doubleMul(value1, -value2), deltaValue);
        //两个负数相乘
        assertEquals(106.399, ToolMath.doubleMul(-value1, -value2), deltaValue);
        //和'0'相乘
        assertEquals(0d, ToolMath.doubleMul(value1, 0d), deltaValue);
        //两个int类型的数据相乘
        assertEquals(15, ToolMath.doubleMul(5, 3), deltaValue);
        //一个int类型和一个double类型的数据相乘
        assertEquals(30.99, ToolMath.doubleMul(value2, 3), deltaValue);
    }

    /**
     * {@link ToolMath#doubleDiv(double, double, int)}单元测试
     */
    @Test
    public void doubleDiv() {
        //保留两位小数点，同时四舍五入
        assertEquals(1.16, ToolMath.doubleDiv(Math.PI,Math.E, 2), deltaValue);
        //保留1位小数点，同时四舍五入
        assertEquals(1.2, ToolMath.doubleDiv(Math.PI, Math.E, 1), deltaValue);
        //保留0位小数点，同时四舍五入
        assertEquals(1, ToolMath.doubleDiv(Math.PI, Math.E, 0), deltaValue);
        //被除数为0时
        assertEquals(0, ToolMath.doubleDiv(Math.PI, 0, 2), deltaValue);
        //保留小数点位数为负数时
        assertEquals(0d, ToolMath.doubleDiv(Math.PI, Math.E, -2), deltaValue);
    }

    /**
     * {@link ToolMath#doubleRound(double, int)}单元测试
     */
    @Test
    public void doubleRound() {
        //保留三位小数点，同时四舍五入
        assertEquals(3.142, ToolMath.doubleRound(Math.PI, 3), deltaValue);
        //保留两位小数点，同时四舍五入
        assertEquals(3.14, ToolMath.doubleRound(Math.PI, 2), deltaValue);
        //保留0位小数点，同时四舍五入
        assertEquals(3, ToolMath.doubleRound(Math.PI, 0), deltaValue);
        //保留小数点位数为负数时
        assertEquals(0, ToolMath.doubleRound(Math.PI, -1), deltaValue);
    }

    /**
     * {@link ToolMath#greatThan(double, double)}单元测试
     */
    @Test
    public void greatThan() {
        assertEquals(true, ToolMath.greatThan(value2, value1));
    }

    /**
     * {@link ToolMath#equalsThan(double, double)}单元测试
     */
    @Test
    public void equalsThan() {
        assertEquals(true, ToolMath.equalsThan(value2, value2));
    }

    /**
     * {@link ToolMath#lessThan(double, double)}单元测试
     */
    @Test
    public void lessThan() {
        assertEquals(true, ToolMath.lessThan(value1, value2));
    }

    /**
     * {@link ToolMath#greatThan(double, double)}单元测试
     */
    @Test
    public void greatEquals() {
        //大于
        assertEquals(true, ToolMath.greatEquals(value2, value1));
        //等于
        assertEquals(true, ToolMath.greatEquals(value1, value1));
    }

    /**
     * {@link ToolMath#lessThan(double, double)}单元测试
     */
    @Test
    public void lessEquals() {
        //小于
        assertEquals(true, ToolMath.lessEquals(value1, value2));
        //等于
        assertEquals(true, ToolMath.lessEquals(value2, value2));
    }
}