package com.lzy.common.tool;

import android.graphics.Bitmap;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * desc: {@link ToolBitmap} 单元测试 <br/>
 * time: 2018/8/1 上午11:17 <br/>
 * author: 匡衡 <br/>
 * since V 1.1
 */
@RunWith(AndroidJUnit4.class)
public class ToolBitmapTest {
    /* 空实例 */
    private final Bitmap nullBitmap = null;
    /* 单色图片实例 -- ARGB8888*/
    private Bitmap newBitmap;
    /* 承载灰色图片实例 -- ARGB8888*/
    private Bitmap newBitmapForGrey;
    /* 单色图片实例 -- RGB565*/
    private Bitmap newBitmapFor565;
    /* 角度参数 */
    private float degrees;

    @Before
    public void setup() {
        degrees = 90f;
        newBitmap = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
        newBitmapFor565 = Bitmap.createBitmap(400, 300, Bitmap.Config.RGB_565);
        newBitmapForGrey = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
    }

    /**
     * {@link ToolBitmap#recycleBitmaps(Bitmap...)}单元测试
     */
    @Test
    public void recycleBitmaps() {
        /* 空运行 */
        ToolBitmap.recycleBitmaps(nullBitmap);

        /* 单个实例 */
        ToolBitmap.recycleBitmaps(newBitmap);
        assertEquals(true, newBitmap.isRecycled());

        /* 多个实例运行，包括null */
        ToolBitmap.recycleBitmaps(newBitmapFor565, newBitmapForGrey, nullBitmap, newBitmap);
        assertEquals(true, newBitmap.isRecycled());
        assertEquals(true, newBitmapFor565.isRecycled());
        assertEquals(true, newBitmapForGrey.isRecycled());
    }

    /**
     * {@link ToolBitmap#rotatingBitmap(float, Bitmap)}单元测试
     */
    @Test
    public void rotatingBitmap() {
        /* 空实例 */
        assertEquals(null, ToolBitmap.rotatingBitmap(degrees, nullBitmap));

        /* 0角度旋转 */
        assertEquals(newBitmap, ToolBitmap.rotatingBitmap(0, newBitmap));

        /* 负角度旋转 */
        assertEquals(newBitmap, ToolBitmap.rotatingBitmap(-90, newBitmap));

        /* 正常运行 */
        assertEquals(true, ToolBitmap.rotatingBitmap(degrees, newBitmap) != null);
    }

    /**
     * {@link ToolBitmap#readPictureDegree(String)}单元测试
     */
    @Test
    public void readPictureDegree() {
        /* 空字符串 */
        assertEquals(0, ToolBitmap.readPictureDegree(""));

        /* null测试 */
        assertEquals(0, ToolBitmap.readPictureDegree(null));

        /* 异常文件路径测试 */
        assertEquals(0, ToolBitmap.readPictureDegree("/file/test"));
    }

    /**
     * {@link ToolBitmap#getBitmapConfig(Bitmap)}单元测试
     */
    @Test
    public void getBitmapConfig() {
        /* 空实例 */
        assertEquals(Bitmap.Config.ARGB_8888, ToolBitmap.getBitmapConfig(nullBitmap));

        /* RGB_8888图片实例 */
        assertEquals(Bitmap.Config.ARGB_8888, ToolBitmap.getBitmapConfig(newBitmap));

        /* RGB_565图片实例 */
        assertEquals(Bitmap.Config.RGB_565, ToolBitmap.getBitmapConfig(newBitmapFor565));
    }

    /**
     * {@link ToolBitmap#changBitmapToGrey(Bitmap, Bitmap)}单元测试
     */
    @Test
    public void changBitmapToGrey() {
        /* 原始图片为空 */
        assertEquals(null, ToolBitmap.changBitmapToGrey(nullBitmap, newBitmap));

        /* 原始图片和承载灰色的图片都为空 */
        assertEquals(null, ToolBitmap.changBitmapToGrey(nullBitmap, nullBitmap));

        /* 承载灰色的图片为空 */
        assertEquals(true, ToolBitmap.changBitmapToGrey(newBitmap, nullBitmap) != null);

        /* 原始图片和承载图片都不为空*/
        assertEquals(true, ToolBitmap.changBitmapToGrey(newBitmap, newBitmapForGrey) != null);
    }

    /**
     * {@link ToolBitmap#getCompressBitmapByConfigAndQuality(Bitmap, Bitmap.Config, int)}单元测试
     */
    @Test
    public void getCompressBitmapByConfigAndQuality() {
        /* 原始图片为空 */
        assertEquals(null, ToolBitmap.getCompressBitmapByConfigAndQuality(nullBitmap, Bitmap.Config.ARGB_8888, 100));

        /* 原始图片不为空 */
        assertEquals(true, ToolBitmap.getCompressBitmapByConfigAndQuality(newBitmap, Bitmap.Config.RGB_565, 50) != null);
    }

    /**
     * {@link ToolBitmap#getCompressBitmap(String, Bitmap.Config, int, int, int)}单元测试
     */
    @Test
    public void getCompressBitmap() {
        /* 文件路径为空 */
        assertEquals(null, ToolBitmap.getCompressBitmap("", Bitmap.Config.ARGB_8888
                , 100, 100, 100));

        /* 目标宽度为0 */
        assertEquals(null, ToolBitmap.getCompressBitmap("/file/test", Bitmap.Config.ARGB_8888
                , 0, 100, 100));

        /* 目标高度为0 */
        assertEquals(null, ToolBitmap.getCompressBitmap("/file/test", Bitmap.Config.ARGB_8888
                , 100, 0, 100));

        /* 文件路径异常 */
        assertEquals(null, ToolBitmap.getCompressBitmap("/file/test", Bitmap.Config.ARGB_8888
                , 100, 100, 100));
    }
}