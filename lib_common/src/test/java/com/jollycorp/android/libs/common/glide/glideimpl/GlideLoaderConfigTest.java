package com.jollycorp.android.libs.common.glide.glideimpl;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.target.Target;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.Assert.*;


/**
 * desc: {@link GlideLoaderConfig} 单元测试
 * time: 2018/8/2 下午3:34
 * author: yangbincai
 * since V 1.0
 */
public class GlideLoaderConfigTest {


    @Mock
    private ImageView mImageView;

    @Mock
    private View mView;

    @Mock
    private Target<Drawable> mTarget;

    @Mock
    private GlideImageLoader mGlideImageLoader;

    @Mock


    private GlideLoaderConfig mGlideLoaderConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mGlideLoaderConfig = new GlideLoaderConfig();
    }

    /**
     * into(ImageView)
     */
    @Test
    public void into() throws Exception{

        assertNotNull(mImageView);
        GlideLoaderConfig glideLoaderConfig = new GlideLoaderConfig();
        glideLoaderConfig.into(mImageView);
        Class<?> clazz = glideLoaderConfig.getClass().getSuperclass();
        Field field = clazz.getDeclaredField("targetImageView");
        field.setAccessible(true);
        ImageView imageView = (ImageView) field.get(glideLoaderConfig);

        //由于into方法中调用了loadOptions又将config的配置重置为null了，故imageView应该为null而不是等于mImageView
        assertNull(imageView);

        mGlideLoaderConfig.into(mImageView);
        assertNull(mGlideLoaderConfig.targetImageView);
        //mock 验证  mGlideImageLoader调用了loadOptions
        mGlideImageLoader.loadOptions(mGlideLoaderConfig);
        Mockito.verify(mGlideImageLoader,Mockito.times(1)).loadOptions(mGlideLoaderConfig);
    }

    /**
     * into(View)
     */
    @Test
    public void into1() throws Exception{
        assertNotNull(mView);
        GlideLoaderConfig glideLoaderConfig = new GlideLoaderConfig();
        glideLoaderConfig.into(mView);
        Class<?> clazz = glideLoaderConfig.getClass();
        Field field = clazz.getDeclaredField("targetView");
        field.setAccessible(true);
        View view = (View) field.get(glideLoaderConfig);

        //由于into方法中调用了loadOptions又将config的配置重置为null了，故view应该为null而不是等于mView
        assertNull(view);

        mGlideLoaderConfig.into(mView);
        assertNull(mGlideLoaderConfig.targetView);

        //mock 验证  mGlideImageLoader调用了loadOptions
        mGlideImageLoader.loadOptions(mGlideLoaderConfig);
        Mockito.verify(mGlideImageLoader,Mockito.times(1)).loadOptions(mGlideLoaderConfig);
    }

    /**
     * into(Target)
     */
    @Test
    public void into2() throws Exception{
        assertNotNull(mTarget);
        GlideLoaderConfig glideLoaderConfig = new GlideLoaderConfig();
        glideLoaderConfig.intoDrawableTarget(mTarget);
        Class<?> clazz = glideLoaderConfig.getClass();
        Field field = clazz.getDeclaredField("targetDrawable");
        field.setAccessible(true);
        Target target = (Target) field.get(glideLoaderConfig);

        //由于into方法中调用了loadOptions又将config的配置重置为null了，故target应该为null而不是等于mTarget
        assertNull(target);

        mGlideLoaderConfig.intoDrawableTarget(mTarget);
        assertNull(mGlideLoaderConfig.targetDrawable);

        //mock 验证  mGlideImageLoader调用了loadOptions
        mGlideImageLoader.loadOptions(mGlideLoaderConfig);
        Mockito.verify(mGlideImageLoader,Mockito.times(1)).loadOptions(mGlideLoaderConfig);
    }
}