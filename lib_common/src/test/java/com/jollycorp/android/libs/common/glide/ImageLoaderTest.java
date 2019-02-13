package com.jollycorp.android.libs.common.glide;


import android.content.Context;
import android.net.Uri;
import android.test.mock.MockContext;

import com.jollycorp.android.libs.common.glide.config.CommonLoaderConfig;
import com.jollycorp.android.libs.common.glide.config.LoaderConfig;
import com.jollycorp.android.libs.common.glide.glideimpl.GlideImageLoader;
import com.jollycorp.android.libs.common.glide.glideimpl.GlideLoaderConfig;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


/**
 * desc: {@link ImageLoader} 单元测试
 * time: 2018/8/2 下午3:34
 * author: yangbincai
 * since V 1.0
 */
public class ImageLoaderTest {


    private IImageLoader<GlideLoaderConfig> mGlideImageLoader;

    private ImageLoader mImageLoader;

    /**
     * 模拟注入的loader
     */
    @Mock
    private IImageLoader<CommonLoaderConfig> mockImageLoader;

    /**
     * 模拟uri
     */
    @Mock
    private Uri uri1;

    /**
     * 模拟uri
     */
    @Mock
    private Uri uri2;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mGlideImageLoader = GlideImageLoader.getInstance();
        mImageLoader = ImageLoader.getInstance();
    }

    @Test
    public void setImageLoaderImp() {
        assertNotNull(mockImageLoader);

        //传入null，实际依旧为mGlideImageLoader
        mImageLoader.setImageLoader(null);
        assertNotNull(mImageLoader.getLoader());
        assertEquals(mGlideImageLoader, mImageLoader.getLoader());

        //传入自己时，应过滤
        mImageLoader.setImageLoader(mImageLoader);
        assertNotEquals(mImageLoader, mImageLoader.getLoader());

        mImageLoader.setImageLoader(mockImageLoader);
        assertEquals(mockImageLoader, mImageLoader.getLoader());

    }

    @Test
    public void getLoader() {
        //默认情况下
        assertNotNull(mImageLoader.getLoader());

        //传入null，实际依旧为mGlideImageLoader
        mImageLoader.setImageLoader(null);
        assertNotNull(mImageLoader.getLoader());

        //传入自己时，应过滤，实际依旧为mGlideImageLoader
        mImageLoader.setImageLoader(mImageLoader);
        assertNotEquals(mImageLoader, mImageLoader.getLoader());

        //注入外部loader后
        mImageLoader.setImageLoader(mockImageLoader);
        assertEquals(mockImageLoader, mImageLoader.getLoader());
    }

    /**
     * 验证load（uri）
     */
    @Test
    public void load() {
        //验证glideLoader
        testGlideLoad4Url();
        //验证注入的loader
        testMockLoad4Url();

    }

    /**
     * 验证注入的loader
     */
    private void testMockLoad4Url() {
        mImageLoader.setImageLoader(mockImageLoader);

        //模拟load方法
        Mockito.when(mockImageLoader.load("abc")).thenReturn(new CommonLoaderConfig().load("abc"));
        LoaderConfig abc_new = mImageLoader.load("abc");
        LoaderConfig abc_mockLoad = mockImageLoader.load("abc");
        Mockito.verify(mockImageLoader, Mockito.times(2)).load("abc");
        //断言
        assertNotNull(abc_mockLoad);
        //ImageLoader和mockImageLoader 调用load后都应返回mock的abc_mock对象
        assertEquals(abc_new, abc_mockLoad);
    }

    /**
     * 验证glideLoader
     */
    private void testGlideLoad4Url() {
        //验证glideLoader
        mImageLoader.setImageLoader(mGlideImageLoader);
        LoaderConfig abc = mImageLoader.load("abc");
        GlideLoaderConfig abc_glide = mGlideImageLoader.load("abc");
        GlideLoaderConfig def_glide = mGlideImageLoader.load("def");

        assertNotNull(abc);
        assertNotNull(abc_glide);
        assertNotNull(def_glide);

        String url3 = null;
        mImageLoader.load(url3);
        //断言实际的loader相同
        assertEquals(mGlideImageLoader, mImageLoader.getLoader());

        //load时 config对象池中对象使用后还未释放，不是同一个对象
        assertNotEquals(abc_glide, abc);
        assertNotEquals(def_glide, abc);

        //断言url
        assertNotEquals(def_glide.url, abc_glide.url);
        //断言 abc 和 abc_glide 实际都是GlideLoaderConfig
        assertEquals(abc.getClass(), abc_glide.getClass());
        assertEquals(((GlideLoaderConfig) abc).url, abc_glide.url);
    }

    /**
     * 验证load（uri）
     */
    @Test
    public void load1() {
        //验证glideLoader
        testGlideLoad4Uri();
        //验证注入的loader
        testMockLoad4Uri();
    }

    /**
     * 验证注入的loader
     */
    private void testMockLoad4Uri() {
        //验证注入的loader
        mImageLoader.setImageLoader(mockImageLoader);
        //模拟load方法
        Mockito.when(mockImageLoader.load(uri1)).thenReturn(new CommonLoaderConfig().load(uri1));
        LoaderConfig abc_new = mImageLoader.load(uri1);
        LoaderConfig abc_mockLoad = mockImageLoader.load(uri1);
        Mockito.verify(mockImageLoader, Mockito.times(2)).load(uri1);
        //断言
        assertNotNull(abc_new);
        assertNotNull(abc_mockLoad);
        //ImageLoader和mockImageLoader 调用load后都应返回mock的abc_mock对象
        assertEquals(abc_new, abc_mockLoad);
    }

    /**
     * 验证glideLoader
     */
    private void testGlideLoad4Uri() {
        Uri uri3 = null;
        assertNotNull(uri1);
        assertNotNull(uri2);
        assertNotEquals(uri1, uri2);

        mImageLoader.load(uri3);
        //验证glideLoader
        mImageLoader.setImageLoader(mGlideImageLoader);
        LoaderConfig abc = mImageLoader.load(uri1);
        GlideLoaderConfig abc_glide = mGlideImageLoader.load(uri1);
        GlideLoaderConfig def_glide = mGlideImageLoader.load(uri2);

        assertNotNull(abc);
        assertNotNull(abc_glide);
        assertNotNull(def_glide);

        //断言实际的loader相同
        assertEquals(mGlideImageLoader, mImageLoader.getLoader());

        //load时 config对象池中对象使用后还未释放，不是同一个对象
        assertNotEquals(abc_glide, abc);
        assertNotEquals(def_glide, abc);

        //断言uri
        assertNotEquals(def_glide.uri, abc_glide.uri);
        //断言 abc 和 abc_glide 实际都是GlideLoaderConfig
        assertEquals(abc.getClass(), abc_glide.getClass());
        assertEquals(((GlideLoaderConfig) abc).uri, abc_glide.uri);
    }

    /**
     * 验证load（file)
     */
    @Test
    public void load2() throws IllegalArgumentException {
        File file1 = new File("/this/is/test/file1");
        File file2 = new File("/this/is/test/file2");
        File file3 = null;

        //验证Glideloader
        testGlideLoad4File(file1, file2, file3);
        //验证注入的loader
        testMockLoad4File(file1, file2);
    }

    /**
     * 验证注入的loader
     */
    private void testMockLoad4File(File file1, File file2) {
        mImageLoader.setImageLoader(mockImageLoader);
        //模拟load方法
        Mockito.when(mockImageLoader.load(file1)).thenReturn(new CommonLoaderConfig().load(file1));
        Mockito.when(mockImageLoader.load(file2)).thenReturn(new CommonLoaderConfig().load(file2));
        LoaderConfig abc_imageLoader1 = mImageLoader.load(file1);
        LoaderConfig abc_imageLoader2 = mImageLoader.load(file2);
        CommonLoaderConfig abc_mock1 = mockImageLoader.load(file1);
        CommonLoaderConfig abc_mock2 = mockImageLoader.load(file2);
        Mockito.verify(mockImageLoader, Mockito.times(2)).load(file1);
        Mockito.verify(mockImageLoader, Mockito.times(2)).load(file2);
        //断言
        assertNotNull(abc_mock1);
        assertNotNull(abc_mock2);
        //ImageLoader和mockImageLoader 调用load后都应返回mock的abc_mock对象
        assertEquals(abc_imageLoader1, abc_mock1);
        assertEquals(abc_imageLoader2, abc_mock2);
        assertNotEquals(abc_imageLoader1, abc_mock2);
        assertEquals(abc_imageLoader1.getClass(), abc_mock2.getClass());
        assertNotEquals(((CommonLoaderConfig) abc_imageLoader1).file, abc_mock2.file);
    }

    /**
     * 验证Glideloader
     */
    private void testGlideLoad4File(File file1, File file2, File file3) {
        mImageLoader.load(file3);

        //验证file不同
        assertNotNull(file1);
        assertNotNull(file2);
        assertNotEquals(file1, file2);

        //验证glideLoader
        mImageLoader.setImageLoader(mGlideImageLoader);
        LoaderConfig abc = mImageLoader.load(file1);
        GlideLoaderConfig abc_glide = mGlideImageLoader.load(file1);
        GlideLoaderConfig def_glide = mGlideImageLoader.load(file2);

        assertNotNull(abc);
        assertNotNull(abc_glide);
        assertNotNull(def_glide);

        //断言实际的loader相同
        assertEquals(mGlideImageLoader, mImageLoader.getLoader());

        //load时 config对象池中对象使用后还未释放，不是同一个对象
        assertNotEquals(abc_glide, abc);
        assertNotEquals(def_glide, abc);

        //断言file
        assertNotEquals(def_glide.file, abc_glide.file);
        //断言 abc 和 abc_glide 实际都是GlideLoaderConfig
        assertEquals(abc.getClass(), abc_glide.getClass());
        assertEquals(((GlideLoaderConfig) abc).file, abc_glide.file);
    }

    /**
     * load（resId）
     */
    @Test
    public void load3() {
        int resId1 = -1;
        int resId2 = 0;
        testGlideLoad4Res(resId1, resId2);
        //验证注入的loader
        testMockLoad4Res(resId1, resId2);
    }

    /**
     * 验证注入的loader
     */
    private void testMockLoad4Res(int resId1, int resId2) {
        mImageLoader.setImageLoader(mockImageLoader);
        //模拟load方法
        Mockito.when(mockImageLoader.load(resId1)).thenReturn(new CommonLoaderConfig().load(resId1));
        Mockito.when(mockImageLoader.load(resId2)).thenReturn(new CommonLoaderConfig().load(resId2));
        LoaderConfig abc_imageLoader1 = mImageLoader.load(resId1);
        LoaderConfig abc_imageLoader2 = mImageLoader.load(resId2);
        CommonLoaderConfig abc_mock1 = mockImageLoader.load(resId1);
        CommonLoaderConfig abc_mock2 = mockImageLoader.load(resId2);
        Mockito.verify(mockImageLoader, Mockito.times(2)).load(resId1);
        Mockito.verify(mockImageLoader, Mockito.times(2)).load(resId2);
        //断言
        assertNotNull(abc_mock1);
        assertNotNull(abc_mock2);
        //ImageLoader和mockImageLoader 调用load后都应返回mock的abc_mock对象
        assertEquals(abc_imageLoader1, abc_mock1);
        assertEquals(abc_imageLoader2, abc_mock2);
        assertNotEquals(abc_imageLoader1, abc_mock2);
        assertEquals(abc_imageLoader1.getClass(), abc_mock2.getClass());
        assertNotEquals(((CommonLoaderConfig) abc_imageLoader1).drawableResId, abc_mock2.drawableResId);
    }

    /**
     * 验证glideLoader
     */
    private void testGlideLoad4Res(int resId1, int resId2) {
        mImageLoader.setImageLoader(mGlideImageLoader);
        LoaderConfig abc = mImageLoader.load(resId1);
        GlideLoaderConfig abc_glide = mGlideImageLoader.load(resId1);
        GlideLoaderConfig def_glide = mGlideImageLoader.load(resId2);

        assertNotNull(abc);
        assertNotNull(abc_glide);
        assertNotNull(def_glide);

        //断言实际的loader相同
        assertEquals(mGlideImageLoader, mImageLoader.getLoader());

        //load时 config对象池中对象使用后还未释放，不是同一个对象
        assertNotEquals(abc_glide, abc);
        assertNotEquals(def_glide, abc);

        //断言file
        assertNotEquals(def_glide.drawableResId, abc_glide.drawableResId);
        //断言 abc 和 abc_glide 实际都是GlideLoaderConfig
        assertEquals(abc.getClass(), abc_glide.getClass());
        assertEquals(((GlideLoaderConfig) abc).drawableResId, abc_glide.drawableResId);
    }

    @Test
    public void clearMemoryCache() {
        assertNotNull(mImageLoader.getLoader());
        mImageLoader.setImageLoader(mockImageLoader);

        mImageLoader.clearMemoryCache(null);
        Mockito.verify(mockImageLoader, Mockito.times(1)).clearMemoryCache(null);

        Context context = new MockContext();
        assertNotNull(context);
        mImageLoader.clearMemoryCache(context);
        Mockito.verify(mockImageLoader, Mockito.times(1)).clearMemoryCache(context);
    }

    @Test
    public void clearDiskCache() {
        assertNotNull(mImageLoader.getLoader());

        mImageLoader.setImageLoader(mockImageLoader);

        assertNotNull(mImageLoader.getLoader());

        mImageLoader.clearDiskCache(null);
        Mockito.verify(mockImageLoader, Mockito.times(1)).clearDiskCache(null);

        Context context = new MockContext();
        assertNotNull(context);
        mImageLoader.clearDiskCache(context);
        Mockito.verify(mockImageLoader, Mockito.times(1)).clearDiskCache(context);
    }

    @Test
    public void setMemoryCache() {
        assertNotNull(mImageLoader.getLoader());

        mImageLoader.setImageLoader(mockImageLoader);

        assertNotNull(mImageLoader.getLoader());

        //验证抛出异常
        mImageLoader.setMemoryCache();
        Mockito.verify(mockImageLoader, Mockito.times(1)).setMemoryCache();

        mImageLoader.setMemoryCache();
        Mockito.verify(mockImageLoader, Mockito.times(2)).setMemoryCache();
    }

    @Test
    public void setDiskCache() {
        assertNotNull(mImageLoader.getLoader());

        mImageLoader.setImageLoader(mockImageLoader);

        assertNotNull(mImageLoader.getLoader());

        //验证抛出异常
        mImageLoader.setDiskCache();
        Mockito.verify(mockImageLoader, Mockito.times(1)).setDiskCache();

        mImageLoader.setDiskCache();
        Mockito.verify(mockImageLoader, Mockito.times(2)).setDiskCache();
    }

    @Test
    public void onLowMemory() {
        assertNotNull(mImageLoader.getLoader());

        mImageLoader.setImageLoader(mockImageLoader);

        assertNotNull(mImageLoader.getLoader());

        mImageLoader.onLowMemory(null);
        Mockito.verify(mockImageLoader, Mockito.times(1)).onLowMemory(null);

        Context context = new MockContext();
        assertNotNull(context);
        mImageLoader.onLowMemory(context);
        Mockito.verify(mockImageLoader, Mockito.times(1)).onLowMemory(context);
    }

    @Test
    public void onTrimMemory() {
        assertNotNull(mImageLoader.getLoader());

        mImageLoader.setImageLoader(mockImageLoader);

        assertNotNull(mImageLoader.getLoader());

        mImageLoader.onTrimMemory(null, 1);
        Mockito.verify(mockImageLoader, Mockito.times(1)).onTrimMemory(null, 1);

        Context context = new MockContext();
        assertNotNull(context);
        mImageLoader.onTrimMemory(context, 1);
        Mockito.verify(mockImageLoader, Mockito.times(1)).onTrimMemory(context, 1);
    }

    @Test
    public void pauseRequests() {
        assertNotNull(mImageLoader.getLoader());

        mImageLoader.setImageLoader(mockImageLoader);

        assertNotNull(mImageLoader.getLoader());

        mImageLoader.pauseRequests(null);
        Mockito.verify(mockImageLoader, Mockito.times(1)).pauseRequests(null);

        Context context = new MockContext();
        assertNotNull(context);
        mImageLoader.pauseRequests(context);
        Mockito.verify(mockImageLoader, Mockito.times(1)).pauseRequests(context);
    }

    @Test
    public void resumeRequests() {
        assertNotNull(mImageLoader.getLoader());

        mImageLoader.setImageLoader(mockImageLoader);

        assertNotNull(mImageLoader.getLoader());

        mImageLoader.resumeRequests(null);
        Mockito.verify(mockImageLoader, Mockito.times(1)).resumeRequests(null);

        Context context = new MockContext();
        assertNotNull(context);
        mImageLoader.resumeRequests(context);
        Mockito.verify(mockImageLoader, Mockito.times(1)).resumeRequests(context);
    }
}