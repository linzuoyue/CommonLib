package com.lzy.common.local;

import android.content.Context;

import com.lzy.common.other.Optional2;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * desc:  {@link SharedPreferenceHelper}单元测试  <br/>
 * time: 2018/9/17 下午2:52 <br/>
 * author: Logan <br/>
 * since V 1.2.0.2 <br/>
 */
public class SharedPreferenceHelperTest {

    private static final String FILE_NAME = "test.sp";
    private SharedPreferenceHelper mSPHelper;

    /* String */
    private final String keyNull = null;
    private final String keyEmptyString = "";
    private final String keyString1 = "key_string_1";
    private final String keyString2 = "key_string_2";
    private final String keyString3 = "key_string_3";
    private final String valueNull = null;
    private final String valueEmptyString = "";
    private final String valueString = "value_string";

    /* boolean */
    private final String keyBoolean1 = "key_boolean_1";

    /* int */
    private final String keyInt1 = "key_int_1";
    private final String keyInt2 = "key_int_2";
    private final String keyInt3 = "key_int_3";

    /* float */
    private final String keyFloat1 = "key_float_1";
    private final String keyFloat2 = "key_float_2";
    private final String keyFloat3 = "key_float_3";

    /* long */
    private final String keyLong1 = "key_long_1";
    private final String keyLong2 = "key_long_2";
    private final String keyLong3 = "key_long_3";

    /* StringSet */
    private final String keyStringSet1 = "key_string_set_1";
    private final String keyStringSet2 = "key_string_set_2";
    private final String keyStringSet3 = "key_string_set_3";

    /* remove key */
    private final String keyRemove1 = "key_remove_1";
    private final String keyRemove2 = "key_remove_2";
    private final String keyRemove3 = "key_remove_3";


    @Before
    public void setUp() {
        Context context = getInstrumentation().getContext();
        mSPHelper = new SharedPreferenceHelper(context, FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * {@link SharedPreferenceHelper#setString(String, String)}单元测试
     */
    @Test
    public void setString() {
        mSPHelper.setString(keyNull, valueString)
                .setString(keyEmptyString, valueString)
                .setString(keyString1, valueString)
                .setString(keyString2, valueNull)
                .setString(keyString3, valueEmptyString)
                .commit();
    }

    /**
     * {@link SharedPreferenceHelper#getString(String, String)}单元测试
     */
    @Test
    public void getString() {
        final String valueDefault = "DefaultValue";

        // key 为 null
        assertEquals(mSPHelper.getString(keyNull, valueDefault), valueString);
        // key 为 零宽度字符串
        assertEquals(mSPHelper.getString(keyEmptyString, valueDefault), valueString);
        // key 非零宽度字符串
        assertEquals(mSPHelper.getString(keyString1, valueDefault), valueString);
        // value 为null，使用默认值
        assertEquals(mSPHelper.getString(keyString2, valueDefault), valueDefault);
        // value 为 零宽度字符串
        assertEquals(mSPHelper.getString(keyString3, valueDefault), valueEmptyString);
    }

    /**
     * {@link SharedPreferenceHelper#setBoolean(String, boolean)}单元测试
     */
    @Test
    public void setBoolean() {
        mSPHelper.setBoolean(keyBoolean1, true).commit();
    }

    /**
     * {@link SharedPreferenceHelper#getBoolean(String, boolean)} 单元测试
     */
    @Test
    public void getBoolean() {
        assertEquals(mSPHelper.getBoolean(keyBoolean1, false), true);
    }

    /**
     * {@link SharedPreferenceHelper#setInt(String, int)}单元测试
     */
    @Test
    public void setInt() {
        mSPHelper.setInt(keyInt1, -100)
                .setInt(keyInt2, 0)
                .setInt(keyInt3, 300)
                .commit();
    }

    /**
     * {@link SharedPreferenceHelper#getInt(String, int)}单元测试
     */
    @Test
    public void getInt() {
        final int defaultValue = -999;
        assertEquals(mSPHelper.getInt(keyInt1, defaultValue), -100);
        assertEquals(mSPHelper.getInt(keyInt2, defaultValue), 0);
        assertEquals(mSPHelper.getInt(keyInt3, defaultValue), 300);
    }

    /**
     * {@link SharedPreferenceHelper#setFloat(String, float)}单元测试
     */
    @Test
    public void setFloat() {
        mSPHelper.setFloat(keyFloat1, -100.1F)
                .setFloat(keyFloat2, 0.2F)
                .setFloat(keyFloat3, 300.3F)
                .commit();
    }

    /**
     * {@link SharedPreferenceHelper#getFloat(String, float)}单元测试
     */
    @Test
    public void getFloat() {
        final float defaultValue = -800.9F;
        assertEquals(mSPHelper.getFloat(keyFloat1, defaultValue), -100.1F, 0);
        assertEquals(mSPHelper.getFloat(keyFloat2, defaultValue), 0.2F, 0);
        assertEquals(mSPHelper.getFloat(keyFloat3, defaultValue), 300.3F, 0);
    }

    /**
     * {@link SharedPreferenceHelper#setLong(String, long)}单元测试
     */
    @Test
    public void setLong() {
        mSPHelper.setLong(keyLong1, -99L)
                .setLong(keyLong2, 0L)
                .setLong(keyLong3, 500L)
                .commit();
    }

    /**
     * {@link SharedPreferenceHelper#getLong(String, long)}单元测试
     */
    @Test
    public void getLong() {
        final long defaultValue = 3000L;
        assertEquals(mSPHelper.getLong(keyLong1, defaultValue), -99L);
        assertEquals(mSPHelper.getLong(keyLong2, defaultValue), -0);
        assertEquals(mSPHelper.getLong(keyLong3, defaultValue), 500);
    }

    /**
     * {@link SharedPreferenceHelper#setStringSet(String, Set)}单元测试
     */
    @Test
    public void setStringSet() {
        Set<String> emptySet = new HashSet<>(2);
        Set<String> englishNameSet = new HashSet<>(3);
        englishNameSet.add("Logan");
        englishNameSet.add("Nice");

        mSPHelper.setStringSet(keyStringSet1, null)
                .setStringSet(keyStringSet2, emptySet)
                .setStringSet(keyStringSet3, englishNameSet)
                .commit();
    }

    /**
     * {@link SharedPreferenceHelper#getStringSet(String, Set)}单元测试
     */
    @Test
    public void getStringSet() {
        final Set<String> defaultValue = new HashSet<>(1);
        assertEquals(mSPHelper.getStringSet(keyStringSet1, defaultValue), defaultValue);
        assertEquals(mSPHelper.getStringSet(keyStringSet2, null).size(), 0);
        assertEquals(mSPHelper.getStringSet(keyStringSet3, null).size(), 2);
    }

    /**
     * {@link SharedPreferenceHelper#hasKey(String)}单元测试
     */
    @Test
    public void hasKey() {
        assertEquals(mSPHelper.hasKey(keyNull), true);
        assertEquals(mSPHelper.hasKey(keyEmptyString), true);
        assertEquals(mSPHelper.hasKey(keyString3), true);
        assertEquals(mSPHelper.hasKey(keyInt1), true);
        // key 不存在
        assertEquals(mSPHelper.hasKey(keyInt1 + "_abc"), false);
    }

    /**
     * {@link SharedPreferenceHelper#getAll()} 单元测试
     */
    @Test
    public void getAll() {
        /*  添加 key */
        final String key = "key_get_all";
        final String value = "value_all";
        assertEquals(mSPHelper.setString(key, value).commit(), true);


        /* 拿到所有的key value值*/
        Optional2<Map<String, ?>> optional2 = mSPHelper.getAll();

        /* 有值 */
        optional2.ifPresent((map) -> {
            assertEquals(map.size() == 0, false);
            assertEquals(map.get(key), value);
            System.out.println("===== map.size:" + map.size());
        });

        /* 无值 */
        assertEquals(optional2.orElseGet(() -> null) == null, false);
    }

    /**
     * {@link SharedPreferenceHelper#commit()}单元测试
     */
    @Test
    public void commit() {
        final String key = "key_commit";
        final String value = "value_commit";

        assertEquals(mSPHelper.setString(key, value).commit(), true);
        assertEquals(mSPHelper.getString(key, null), value);
    }

    /**
     * {@link SharedPreferenceHelper#apply()}单元测试
     */
    @Test
    public void apply() {
        final String key = "key_apply";
        final String value = "value_apply";

        mSPHelper.setString(key, value).apply();

        assertEquals(mSPHelper.getString(key, null), value);
    }

    /**
     * {@link SharedPreferenceHelper#removeKeys(String...)}单元测试
     */
    @Test
    public void removeKeys() {
        /* 添加 key */
        boolean isSuccess = mSPHelper.setString(keyRemove1, "OK")
                .setInt(keyRemove2, 2)
                .setBoolean(keyRemove3, true)
                .commit();
        assertEquals(isSuccess, true);

        /* 删除 keys */
        mSPHelper.removeKeys(keyRemove1, keyRemove2, keyRemove3);

        assertEquals(mSPHelper.getString(keyRemove1, "defaultValue"), "defaultValue");
        assertEquals(mSPHelper.getInt(keyRemove2, 10), 10);
        assertEquals(mSPHelper.getBoolean(keyRemove3, false), false);
    }

    /**
     * {@link SharedPreferenceHelper#clearAll(boolean)} 单元测试
     */
    @Test
    public void clearAll() {
        // // TODO: 2018/9/17 只能单独运行，否则整体会失败。
//        /* 添加 key */
//        boolean isSuccess = mSPHelper.setString(keyRemove1, "OK")
//                .setInt(keyRemove2, 2)
//                .setBoolean(keyRemove3, true)
//                .commit();
//
//        assertEquals(isSuccess, true);
//
//        /* 删除 keys */
//        mSPHelper.clearAll(true);
//
//        // 验证
//        assertEquals(mSPHelper.getAll().size(), 0);
    }

}