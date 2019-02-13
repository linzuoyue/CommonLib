package com.jollycorp.android.libs.common.glide.glideimpl;

import android.content.Context;
import android.test.mock.MockContext;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.assertNotNull;


/**
 * desc: {@link GlideLoaderImpl} 单元测试
 * time: 2018/8/2 下午3:34
 * author: yangbincai
 * since V 1.0
 */
public class GlideLoaderImplTest {


    @Mock
    private GlideLoaderImpl mMockGlideLoader;


    @Mock
    private RequestManager mGlideRequests;

    @Mock
    private Glide mGlide;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Context context = new MockContext();
    }

    @Test
    public void clearMemoryCache() {
        Context context = new MockContext();
        mMockGlideLoader.clearMemoryCache(context);
        Mockito.verify(mMockGlideLoader, Mockito.times(1)).clearMemoryCache(context);

        assertNotNull(mGlide);
        mGlide.clearMemory();
        Mockito.verify(mGlide, Mockito.times(1)).clearMemory();
    }

    @Test
    public void clearDiskCache() {
        Context context = new MockContext();
        mMockGlideLoader.clearDiskCache(context);
        Mockito.verify(mMockGlideLoader, Mockito.times(1)).clearDiskCache(context);
        assertNotNull(mGlide);
        mGlide.clearDiskCache();
        Mockito.verify(mGlide, Mockito.times(1)).clearDiskCache();
    }

    @Test
    public void onLowMemory() {
        Context context = new MockContext();
        mMockGlideLoader.onLowMemory(context);
        Mockito.verify(mMockGlideLoader, Mockito.times(1)).onLowMemory(context);

        assertNotNull(mGlide);
        mGlide.onLowMemory();
        Mockito.verify(mGlide, Mockito.times(1)).onLowMemory();
    }

    @Test
    public void onTrimMemory() {
        Context context = new MockContext();
        mMockGlideLoader.onTrimMemory(context, 1);
        Mockito.verify(mMockGlideLoader, Mockito.times(1)).onTrimMemory(context, 1);

        assertNotNull(mGlide);
        mGlide.onTrimMemory(1);
        Mockito.verify(mGlide, Mockito.times(1)).onTrimMemory(1);
        mGlide.onTrimMemory(0);
        Mockito.verify(mGlide, Mockito.times(1)).onTrimMemory(0);
        mGlide.onTrimMemory(-1);
        Mockito.verify(mGlide, Mockito.times(1)).onTrimMemory(-1);
    }

    @Test
    public void pauseRequests() {
        Context context = new MockContext();
        mMockGlideLoader.pauseRequests(context);
        Mockito.verify(mMockGlideLoader, Mockito.times(1)).pauseRequests(context);

        assertNotNull(mGlideRequests);
        mGlideRequests.pauseRequests();
        Mockito.verify(mGlideRequests, Mockito.times(1)).pauseRequests();
    }

    @Test
    public void resumeRequests() {
        Context context = new MockContext();
        mMockGlideLoader.resumeRequests(context);
        Mockito.verify(mMockGlideLoader, Mockito.times(1)).resumeRequests(context);

        assertNotNull(mGlideRequests);
        mGlideRequests.resumeRequests();
        Mockito.verify(mGlideRequests, Mockito.times(1)).resumeRequests();
    }
}