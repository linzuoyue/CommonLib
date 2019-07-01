package com.lzy.glide.glide.config;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools;

import com.lzy.glide.config.BaseConfigFactory;
import com.lzy.glide.config.LoaderConfig;
import com.lzy.glide.glideimpl.GlideLoaderConfig;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BaseConfigFactoryTest<T extends LoaderConfig> {


    @Mock
    private Pools.SynchronizedPool<T> mockSyncPool;

    @Mock
    private T mT;

    @Mock
    private BaseConfigFactory<T> mBaseConfigFactory;
    private BaseConfigFactory<GlideLoaderConfig> mGlideFactory = new BaseConfigFactory<GlideLoaderConfig>(5) {
        @NonNull
        @Override
        public GlideLoaderConfig createNewInstance() {
            return new GlideLoaderConfig();
        }
    };

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mBaseConfigFactory.getSyncPool()).thenReturn(mockSyncPool);
        Mockito.when(mockSyncPool.acquire()).thenReturn(null);
        Mockito.when(mBaseConfigFactory.createNewInstance()).thenReturn(mT);
    }

    @Test
    public void obtain() {
        //mock
        Pools.SynchronizedPool<T> syncPool = mBaseConfigFactory.getSyncPool();
        assertNotNull(mockSyncPool);
        assertEquals(mockSyncPool, syncPool);
        //模拟acquire
        T t = syncPool.acquire();
        assertNull(t);
        //模拟createNewInstance
        T newInstance = mBaseConfigFactory.createNewInstance();
        assertNotNull(newInstance);
        //模拟acquire
        Mockito.when(mockSyncPool.acquire()).thenReturn(mT);
        T t2 = syncPool.acquire();
        assertNotNull(t2);

        //mGlideFactory
        Pools.SynchronizedPool<GlideLoaderConfig> glideFactorySyncPool = mGlideFactory.getSyncPool();
        //验证首次acquire
        GlideLoaderConfig glideLoaderConfig = glideFactorySyncPool.acquire();
        assertNull(glideLoaderConfig);
        //验证createNewInstance
        GlideLoaderConfig instance = mGlideFactory.createNewInstance();
        assertNotNull(instance);
        //验证release后acquire
        glideFactorySyncPool.release(instance);
        GlideLoaderConfig newLoaderConfig = glideFactorySyncPool.acquire();
        assertNotNull(newLoaderConfig);
        assertEquals(newLoaderConfig, instance);
    }

    @Test
    public void createNewInstance() {
        T t = mBaseConfigFactory.createNewInstance();
        assertNotNull(t);
        assertEquals(mT, t);

        //验证mGlideFactory createNewInstance
        GlideLoaderConfig instance = mGlideFactory.createNewInstance();
        assertNotNull(instance);
    }

    @Test
    public void release() {
        //mock验证
        assertNotNull(mT);
        mT.reset();
        Mockito.verify(mT,Mockito.times(1)).reset();
        Pools.SynchronizedPool<T> syncPool = mBaseConfigFactory.getSyncPool();
        syncPool.release(mT);
        Mockito.verify(syncPool,Mockito.times(1)).release(mT);

        //mGlideFactory验证
        Pools.SynchronizedPool<GlideLoaderConfig> glideFactorySyncPool = mGlideFactory.getSyncPool();
        GlideLoaderConfig acquire = glideFactorySyncPool.acquire();
        assertNull(acquire);
        GlideLoaderConfig glideLoaderConfig = new GlideLoaderConfig();
        glideFactorySyncPool.release(glideLoaderConfig);
        GlideLoaderConfig newGlideLoaderConfig = glideFactorySyncPool.acquire();
        assertNotNull(newGlideLoaderConfig);
    }
}