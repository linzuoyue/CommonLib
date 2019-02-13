package com.jollycorp.android.libs.common.other;

import com.jollycorp.android.libs.common.other.lambda.Supplier2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * desc: {@link Optional2}单元测试 <br/>
 * time: 2018/8/30 下午5:43 <br/>
 * author: Logan <br/>
 * since V 1.2 <br/>
 */
public class Optional2Test {

    private final static Integer NUMBER = 33;

    private final Object nullObj = null;
    private final String length0Str = "";
    private final Integer numberInt = NUMBER;
    private final Integer numberHundred = 100;


    /**
     * {@link Optional2#ofNullable(Object)}测试
     */
    @Test
    public void ofNullable() {
        assertEquals(false, Optional2.ofNullable(nullObj).isPresent());
        assertEquals(true, Optional2.ofNullable(length0Str).isPresent());
        assertEquals(true, Optional2.ofNullable(numberInt).isPresent());
    }

    /**
     * {@link Optional2#empty()}测试
     */
    @Test
    public void empty() {
        Optional2 emptyOptional = Optional2.empty();
        assertEquals(false, emptyOptional.isPresent());
        assertEquals(true, (emptyOptional == Optional2.empty()));
    }

    /**
     * {@link Optional2#get()}测试
     */
    @Test
    public void get() {
        /* null情况 */
        Optional2<Object> nullOptional = Optional2.ofNullable(nullObj);
        nullOptional.ifPresent(value -> {
            assertEquals(false, true);
        });
        assertEquals("empty", nullOptional.orElse("empty"));

        assertEquals("", Optional2.ofNullable(length0Str).get());
        assertEquals(Integer.valueOf(33), Optional2.ofNullable(numberInt).get());
    }

    /**
     * {@link Optional2#isPresent()}测试
     */
    @Test
    public void isPresent() {
        assertEquals(false, Optional2.ofNullable(nullObj).isPresent());
        assertEquals(true, Optional2.ofNullable(length0Str).isPresent());
        assertEquals(true, Optional2.ofNullable(numberInt).isPresent());
    }

    /**
     * {@link Optional2#isPresent()}测试
     */
    @Test
    public void ifPresent() {
        Optional2.ofNullable(nullObj).ifPresent(value -> {
            assertEquals(false, true);
        });

        Optional2.ofNullable(length0Str)
                .ifPresent(value -> assertEquals("", value));

        Optional2.ofNullable(numberInt)
                .ifPresent(value -> assertEquals(NUMBER, value));
    }

    /**
     * {@link Optional2#orElse(Object)}测试
     */
    @Test
    public void orElse() {
        assertEquals("Null", Optional2.ofNullable(nullObj).orElse("Null"));
        assertEquals("", Optional2.ofNullable(length0Str).orElse("Empty"));
        assertEquals(NUMBER, Optional2.ofNullable(numberInt).orElse(1000));
    }

    /**
     * {@link Optional2#orElseGet(Supplier2)}测试
     */
    @Test
    public void orElseGet() {
        assertEquals(null, Optional2.ofNullable(nullObj).orElseGet(() -> null));
        assertEquals("Null", Optional2.ofNullable(nullObj).orElseGet(() -> {
            System.out.println("走这里....");
            return "Null";
        }));

        assertEquals("", Optional2.ofNullable(length0Str).orElseGet(() -> {
            assertEquals(true, false);
            return "Null";
        }));

        assertEquals(NUMBER, Optional2.ofNullable(numberInt).orElseGet(() -> {
            assertEquals(true, false);
            return 0;
        }));
    }

    /**
     * {@link Optional2#equals(Object)}测试
     */
    @Test
    public void testEquals() {
        assertEquals(true, Optional2.ofNullable(nullObj).equals(Optional2.ofNullable(nullObj)));
        assertEquals(true, Optional2.ofNullable(length0Str).equals(Optional2.ofNullable(length0Str)));
        assertEquals(true, Optional2.ofNullable(numberInt).equals(Optional2.ofNullable(numberInt)));
        assertEquals(false, Optional2.ofNullable(numberInt).equals(Optional2.ofNullable(numberHundred)));
    }

}