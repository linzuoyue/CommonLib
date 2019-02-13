package com.jollycorp.android.libs.common.glide.glideimpl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.lang.reflect.Field;

import static org.junit.Assert.*;


/**
 * desc: {@link GlideImageLoader} 单元测试
 * time: 2018/8/2 下午3:34
 * author: yangbincai
 * since V 1.0
 */
public class GlideImageLoaderTest {


    private GlideConfigFactory mGlideConfigFactory;

    private String url = "this is url";

    @Mock
    private Uri uri;

    @Mock
    private File file;

    private int drawableRes;

    @Mock
    private GlideLoaderImpl mGlideLoader;
    private GlideLoaderConfig mLoaderConfig;


    @Before
    public void setUp() throws Exception {
        mGlideConfigFactory = GlideConfigFactory.getInstance();
        mLoaderConfig = mGlideConfigFactory.obtain();
        MockitoAnnotations.initMocks(this);
    }

    /**
     * load(url)
     */
    @Test
    public void load() throws Exception{
        assertNotNull(mLoaderConfig);

        GlideLoaderConfig config = mLoaderConfig.load(url);
        Class<?> clazz = config.getClass().getSuperclass();
        Field field = clazz.getDeclaredField("url");
        field.setAccessible(true);
        String myUrl = (String) field.get(config);
        assertEquals(myUrl, url);

        String testNull = null;
        GlideLoaderConfig config2 = mLoaderConfig.load(testNull);
        Class<?> clazz2 = config2.getClass().getSuperclass();
        Field field2 = clazz2.getDeclaredField("url");
        field2.setAccessible(true);
        String myUrl2 = (String) field2.get(config2);
        assertNull(myUrl2);
    }

    /**
     * load(uri)
     */
    @Test
    public void load1() throws Exception{
        assertNotNull(mLoaderConfig);

        GlideLoaderConfig config = mLoaderConfig.load(uri);
        Class<?> clazz = config.getClass().getSuperclass();
        Field field = clazz.getDeclaredField("uri");
        field.setAccessible(true);
        Uri myUri = (Uri) field.get(config);
        assertEquals(myUri, uri);

        Uri testNull = null;
        GlideLoaderConfig config2 = mLoaderConfig.load(testNull);
        Class<?> clazz2 = config2.getClass().getSuperclass();
        Field field2 = clazz2.getDeclaredField("uri");
        field2.setAccessible(true);
        Uri myUri2 = (Uri) field2.get(config2);
        assertNull(myUri2);
    }

    /**
     * load(file)
     */
    @Test
    public void load2() throws Exception{
        assertNotNull(mLoaderConfig);

        GlideLoaderConfig config = mLoaderConfig.load(file);
        Class<?> clazz = config.getClass().getSuperclass();
        Field field = clazz.getDeclaredField("file");
        field.setAccessible(true);
        File myfile = (File) field.get(config);
        assertEquals(myfile, file);

        File testNull = null;
        GlideLoaderConfig config2 = mLoaderConfig.load(testNull);
        Class<?> clazz2 = config2.getClass().getSuperclass();
        Field field2 = clazz2.getDeclaredField("file");
        field2.setAccessible(true);
        File myfile2 = (File) field2.get(config2);
        assertNull(myfile2);
    }

    /**
     * load(resId)
     */
    @Test
    public void load3() throws Exception{
        int res1 = -1;
        int res2 = 0;

        assertNotNull(mLoaderConfig);

        GlideLoaderConfig config = mLoaderConfig.load(res1);
        Class<?> clazz = config.getClass().getSuperclass();
        Field field = clazz.getDeclaredField("drawableResId");
        field.setAccessible(true);
        int drawableResId = (int) field.get(config);
        assertEquals(drawableResId, res1);

        GlideLoaderConfig config2 = mLoaderConfig.load(res2);
        Class<?> clazz2 = config2.getClass().getSuperclass();
        Field field2 = clazz2.getDeclaredField("drawableResId");
        field2.setAccessible(true);
        int drawableResId2 = (int) field2.get(config2);
        assertEquals(0,drawableResId2);

    }

    @Test
    public void loadOptions() {
        assertNotNull(mGlideLoader);
        mGlideLoader.loadImage(mLoaderConfig);
        Mockito.verify(mGlideLoader,Mockito.times(1)).loadImage(mLoaderConfig);
        mGlideConfigFactory.release(mLoaderConfig);
        GlideLoaderConfig obtain = mGlideConfigFactory.obtain();
        assertEquals(obtain, mLoaderConfig);
    }

    @Test
    public void clearMemoryCache() {
        assertNotNull(mGlideLoader);
        Context context = new MockContext();
        mGlideLoader.clearMemoryCache(context);
        Mockito.verify(mGlideLoader,Mockito.times(1)).clearMemoryCache(context);
    }

    @Test
    public void clearDiskCache() {
        assertNotNull(mGlideLoader);
        Context context = new MockContext();
        mGlideLoader.clearDiskCache(context);
        Mockito.verify(mGlideLoader,Mockito.times(1)).clearDiskCache(context);
    }

    @Test
    public void setMemoryCache() {
        assertNotNull(mGlideLoader);
        mGlideLoader.setMemoryCache();
        Mockito.verify(mGlideLoader,Mockito.times(1)).setMemoryCache();
    }

    @Test
    public void setDiskCache() {
        assertNotNull(mGlideLoader);
        mGlideLoader.setDiskCache();
        Mockito.verify(mGlideLoader,Mockito.times(1)).setDiskCache();
    }

    @Test
    public void onLowMemory() {
        assertNotNull(mGlideLoader);
        Context context = new MockContext();
        mGlideLoader.onLowMemory(context);
        Mockito.verify(mGlideLoader,Mockito.times(1)).onLowMemory(context);
    }

    @Test
    public void onTrimMemory() {
        assertNotNull(mGlideLoader);
        Context context = new MockContext();
        int level = 0;
        mGlideLoader.onTrimMemory(context, level);
        Mockito.verify(mGlideLoader, Mockito.times(1)).onTrimMemory(context, level);
    }

    @Test
    public void pauseRequests() {
        assertNotNull(mGlideLoader);
        Context context = new MockContext();
        mGlideLoader.pauseRequests(context);
        Mockito.verify(mGlideLoader,Mockito.times(1)).pauseRequests(context);
    }

    @Test
    public void resumeRequests() {
        assertNotNull(mGlideLoader);
        Context context = new MockContext();
        mGlideLoader.resumeRequests(context);
        Mockito.verify(mGlideLoader,Mockito.times(1)).resumeRequests(context);
    }
}