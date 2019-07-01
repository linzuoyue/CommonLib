package com.lzy.glide.glide.glideimpl;

import com.lzy.glide.glideimpl.GlideConfigFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


/**
 * desc: {@link GlideConfigFactory} 单元测试
 * time: 2018/8/2 下午3:34
 * author: yangbincai
 * since V 1.0
 */
public class GlideConfigFactoryTest {

    private GlideConfigFactory mConfigFactory;

    @Before
    public void setUp() throws Exception {
        mConfigFactory = GlideConfigFactory.getInstance();
    }

    @Test
    public void createNewInstance() {
        assertNotNull(mConfigFactory.createNewInstance());
    }
}