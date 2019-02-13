package com.lzy.common.tool;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * desc: {@link ToolList} 单元测试 <br/>
 * time: 2018/7/24 下午3:34 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class ToolListTest {

    /* null list */
    private final List<String> nullList = null;

    /* 没有元素的list */
    private final List<Double> noElementList = Arrays.asList();

    /* 1个null元素的list */
    private List<String> oneNullElementList = new ArrayList<>(1);

    /* 1个元素非Null的list */
    private final List<String> oneNotNullElementList = Arrays.asList("OK");

    /* 多个元素的list */
    private final List<String> moreElementList = Arrays.asList("Hello", "B", "Test");


    @Before
    public void setUp() {
        oneNullElementList.add(null);
    }

    /**
     * {@link ToolList#isNullOrEmpty(List)} 单元测试
     */
    @Test
    public void isNullOrEmpty() {
        /* null list */
        assertEquals(true, ToolList.isNullOrEmpty(nullList));

        /* 没有元素的list */
        assertEquals(true, ToolList.isNullOrEmpty(noElementList));

        /* 1个null元素的list */
        assertEquals(false, ToolList.isNullOrEmpty(oneNullElementList));

        /* 1个元素非Null的list */
        assertEquals(false, ToolList.isNullOrEmpty(oneNotNullElementList));

        /* 多个元素的list */
        assertEquals(false, ToolList.isNullOrEmpty(moreElementList));
    }

    /**
     * {@link ToolList#isNotEmpty(List)} 单元测试
     */
    @Test
    public void isNotEmpty() {
        /* null list */
        assertEquals(false, ToolList.isNotEmpty(nullList));

        /* 没有元素的list */
        assertEquals(false, ToolList.isNotEmpty(noElementList));

        /* 1个null元素的list */
        assertEquals(true, ToolList.isNotEmpty(oneNullElementList));

        /* 1个元素非Null的list */
        assertEquals(true, ToolList.isNotEmpty(oneNotNullElementList));

        /* 多个元素的list */
        assertEquals(true, ToolList.isNotEmpty(moreElementList));
    }

    /**
     * {@link ToolList#getSize(List)} 单元测试
     */
    @Test
    public void getSize() {
        /* null list */
        assertEquals(0, ToolList.getSize(nullList));

        /* 没有元素的list */
        assertEquals(0, ToolList.getSize(noElementList));

        /* 1个null元素的list */
        int oneElement = 1;
        assertEquals(oneElement, ToolList.getSize(oneNullElementList));

        /* 1个元素非Null的list */
        assertEquals(oneElement, ToolList.getSize(oneNotNullElementList));

        /* 多个元素的list */
        int threeElement = 3;
        assertEquals(threeElement, ToolList.getSize(moreElementList));
    }

    /**
     * {@link ToolList#copy(List, List)}单元测试
     */
    @Test
    public void copy() {
        testCopyWhenSourceListIsNull();
        testCopyWhenDestListIsNull();
        testCopyWhenSourceListHasOneNullElement();
        testCopyWhenSourceListHasOneElement();
        testCopyWhenSourceListHasMoreElement();
    }

    /**
     * 测试 {@link ToolList#copy(List, List)} sourceList is null 的情况
     */
    private void testCopyWhenSourceListIsNull() {
        List<String> desList = new ArrayList<>(1);
        ToolList.copy(nullList, desList);
        assertEquals(desList.size(), 0);
    }

    /**
     * 测试 {@link ToolList#copy(List, List)} desList is null 的情况
     */
    private void testCopyWhenDestListIsNull() {
        List<String> desList = null;
        ToolList.copy(oneNotNullElementList, desList);
        assertEquals(desList, null);
    }

    /**
     * 测试 {@link ToolList#copy(List, List)} sourceList 元素只包含一个null元素的情况
     */
    private void testCopyWhenSourceListHasOneNullElement() {
        List<String> desList = new ArrayList<>(oneNullElementList.size());
        ToolList.copy(oneNullElementList, desList);

        for (int i = 0; i < oneNullElementList.size(); i++) {
            assertNull(oneNullElementList.get(i));
            assertEquals(oneNullElementList.get(i), desList.get(i));
        }
    }

    /**
     * 测试 {@link ToolList#copy(List, List)} sourceList 元素只包含一个元素的情况
     */
    private void testCopyWhenSourceListHasOneElement() {
        List<String> desList = new ArrayList<>(oneNotNullElementList.size());
        ToolList.copy(oneNotNullElementList, desList);
        for (int i = 0; i < oneNotNullElementList.size(); i++) {
            assertNotNull(oneNotNullElementList.get(i));
            assertEquals(oneNotNullElementList.get(i), desList.get(i));
        }
    }

    /**
     * 测试 {@link ToolList#copy(List, List)} sourceList 元素只包含多个元素的情况
     */
    private void testCopyWhenSourceListHasMoreElement() {
        List<String> desList = new ArrayList<>(moreElementList.size());
        ToolList.copy(moreElementList, desList);

        for (int i = 0; i < moreElementList.size(); i++) {
            assertEquals(moreElementList.get(i), desList.get(i));
        }
    }

    /**
     * {@link ToolList#clear(List)} 单元测试
     */
    @Test
    public void clear() {
        /* null list */
        ToolList.clear(nullList);
        assertEquals(true, ToolList.isNullOrEmpty(nullList));

        /* 没有元素的list */
        ToolList.clear(noElementList);
        assertEquals(true, ToolList.isNullOrEmpty(noElementList));

        /* 1个null元素的list */
        ToolList.clear(oneNullElementList);
        assertEquals(true, ToolList.isNullOrEmpty(oneNullElementList));

        /* 1个元素非Null的list，Arrays.asList()不支持clear */
        // ToolList.clear(oneNotNullElementList);
        // assertEquals(true, ToolList.isNullOrEmpty(oneNotNullElementList));

        /* 多个元素的list，Arrays.asList()不支持clear*/
        // ToolList.clear(moreElementList);
        // assertEquals(true, ToolList.isNullOrEmpty(moreElementList));
    }


}