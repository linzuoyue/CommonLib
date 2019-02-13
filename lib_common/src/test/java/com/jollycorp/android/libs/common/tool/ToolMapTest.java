package com.lzy.common.tool;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * desc: {@link ToolMap} 单元测试 <br/>
 * time: 2018/7/25 下午2:38 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class ToolMapTest {

    /* null map */
    private final Map<String, String> nullMap = null;

    /* 没有元素的map */
    private final Map<String, String> noElementMap = new HashMap<>(0);

    /* 1个null元素的map */
    private Map<String, String> oneNullElementMap = new HashMap<String, String>(1) {{
        put(null, null);
    }};

    /* 1个元素非Null的map */
    private final Map<String, String> oneNotNullElementMap = new HashMap<String, String>(1) {{
        put("key1", "value1");
    }};

    /* 多个元素的map */
    private final Map<String, String> moreElementMap = new HashMap<String, String>(1) {{
        put("key1", "value1");
        put("key2", "value2");
    }};


    @Before
    public void setUp() throws Exception {

    }

    /**
     * {@link ToolMap#isNullOrEmpty(Map)} 单元测试
     */
    @Test
    public void isNullOrEmpty() {
        /* null map */
        assertEquals(true, ToolMap.isNullOrEmpty(nullMap));

        /* 没有元素的map */
        assertEquals(true, ToolMap.isNullOrEmpty(noElementMap));

        /* 1个null元素的map */
        assertEquals(false, ToolMap.isNullOrEmpty(oneNullElementMap));

        /* 1个元素非Null的map */
        assertEquals(false, ToolMap.isNullOrEmpty(oneNotNullElementMap));

        /* 多个元素的map */
        assertEquals(false, ToolMap.isNullOrEmpty(moreElementMap));
    }

    /**
     * {@link ToolMap#isNotEmpty(Map)} 单元测试
     */
    @Test
    public void isNotEmpty() {
        /* null map */
        assertEquals(false, ToolMap.isNotEmpty(nullMap));

        /* 没有元素的map */
        assertEquals(false, ToolMap.isNotEmpty(noElementMap));

        /* 1个null元素的map */
        assertEquals(true, ToolMap.isNotEmpty(oneNullElementMap));

        /* 1个元素非Null的map */
        assertEquals(true, ToolMap.isNotEmpty(oneNotNullElementMap));

        /* 多个元素的map */
        assertEquals(true, ToolMap.isNotEmpty(moreElementMap));
    }

    /**
     * {@link ToolMap#getSize(Map)} 单元测试
     */
    @Test
    public void getSize() {
        /* null map */
        assertEquals(0, ToolMap.getSize(nullMap));

        /* 没有元素的map */
        assertEquals(0, ToolMap.getSize(noElementMap));

        /* 1个null元素的map */
        int oneElement = 1;
        assertEquals(oneElement, ToolMap.getSize(oneNullElementMap));

        /* 1个元素非Null的map */
        assertEquals(oneElement, ToolMap.getSize(oneNotNullElementMap));

        /* 多个元素的map */
        int threeElement = 2;
        assertEquals(threeElement, ToolMap.getSize(moreElementMap));
    }

    /**
     * {@link ToolMap#copy(Map, Map)} 单元测试
     */
    @Test
    public void copy() {
        testCopyWhenSourceMapIsNull();
        testCopyWhenDestMapIsNull();
        testCopyWhenSourceMapHasOneNullElement();
        testCopyWhenSourceMapHasOneElement();
        testCopyWhenSourceListHasMoreElement();
    }

    /**
     * 测试 {@link ToolMap#copy(Map, Map)} source map is null 的情况
     */
    private void testCopyWhenSourceMapIsNull() {
        Map<String, String> destMap = new HashMap<>(1);
        ToolMap.copy(nullMap, destMap);
        assertEquals(destMap.size(), 0);
    }

    /**
     * 测试 {@link ToolMap#copy(Map, Map)} destMap is null 的情况
     */
    private void testCopyWhenDestMapIsNull() {
        Map<String, String> destMap = null;
        ToolMap.copy(oneNotNullElementMap, destMap);
        assertEquals(destMap, null);
    }

    /**
     * 测试 {@link ToolMap#copy(Map, Map)} 没有元素的Map 的情况
     */
    private void testCopyWhenSourceMapHasOneNullElement() {
        Map<String, String> destMap = new HashMap<>(oneNotNullElementMap.size());
        ToolMap.copy(oneNotNullElementMap, destMap);

        for (String key : oneNotNullElementMap.keySet()) {
            assertEquals(oneNotNullElementMap.get(key), destMap.get(key));
        }
    }

    /**
     * 测试 {@link ToolMap#copy(Map, Map)} 1个null元素的map 的情况
     */
    private void testCopyWhenSourceMapHasOneElement() {
        Map<String, String> destMap = new HashMap<>(oneNullElementMap.size());
        ToolMap.copy(oneNullElementMap, destMap);

        assertNull(destMap.get("key1"));

        for (String key : oneNullElementMap.keySet()) {
            assertNull(oneNullElementMap.get(key));
            assertEquals(oneNullElementMap.get(key), destMap.get(key));
        }
    }

    /**
     * 测试 {@link ToolMap#copy(Map, Map)} 多个元素的Map 的情况
     */
    private void testCopyWhenSourceListHasMoreElement() {
        Map<String, String> destMap = new HashMap<>(moreElementMap.size());
        ToolMap.copy(moreElementMap, destMap);

        assertEquals("value1", destMap.get("key1"));

        for (String key : moreElementMap.keySet()) {
            assertNotNull(moreElementMap.get(key));
            assertEquals(moreElementMap.get(key), destMap.get(key));
        }
    }

    /**
     * 测试 {@link ToolMap#putNotNull(Map, Object, Object)} 多个元素的Map 的情况
     */
    @Test
    public void putNotNull() {
        /* map、key、value 是 null */
        ToolMap.putNotNull(nullMap, null, null);
        assertEquals(true, (nullMap == null));

        /* key、value 是 null */
        ToolMap.putNotNull(noElementMap, null, null);
        assertEquals(true, (noElementMap.size() == 0));

        /* key 是 null */
        ToolMap.putNotNull(noElementMap, null, "value");
        assertEquals(true, (noElementMap.size() == 0));

        /* value 是 null */
        ToolMap.putNotNull(noElementMap, "key", null);
        assertEquals(true, (noElementMap.size() == 0));

        /* key、value 是零宽度字符串 */
        final Map<String, String> map4ZeroStr = new HashMap<>(3);
        ToolMap.putNotNull(map4ZeroStr, "", "");
        ToolMap.putNotNull(map4ZeroStr, "", "");
        assertEquals(true, (map4ZeroStr.size() == 1));

        /* key、value 零宽度字符串、非零宽度字符串均有 */
        final Map<String, String> map1 = new HashMap<>(3);
        ToolMap.putNotNull(map1, "", "value");
        ToolMap.putNotNull(map1, "key", "value");
        assertEquals(true, (map1.size() == 2));

        /* key、value 非零宽度字符串均有 */
        final Map<String, String> map2 = new HashMap<>(3);
        ToolMap.putNotNull(map2, "key1", "value");
        ToolMap.putNotNull(map2, "key2", "value");
        assertEquals(true, (map2.size() == 2));
    }

    @Test
    public void clear() {
        /* null map */
        ToolMap.clear(nullMap);
        assertEquals(true, ToolMap.isNullOrEmpty(nullMap));

        /* 没有元素的map */
        /* 没有元素的list */
        ToolMap.clear(noElementMap);
        assertEquals(true, ToolMap.isNullOrEmpty(noElementMap));

        /* 1个null元素的map */
        ToolMap.clear(oneNullElementMap);
        assertEquals(true, ToolMap.isNullOrEmpty(oneNullElementMap));

        /* 1个元素非Null的map */
        ToolMap.clear(oneNotNullElementMap);
        assertEquals(true, ToolMap.isNullOrEmpty(oneNotNullElementMap));

        /* 多个元素的map */
        ToolMap.clear(moreElementMap);
        assertEquals(true, ToolMap.isNullOrEmpty(moreElementMap));
    }

}
