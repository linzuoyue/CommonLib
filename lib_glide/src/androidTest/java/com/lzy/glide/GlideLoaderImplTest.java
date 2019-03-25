package com.lzy.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jollycorp.android.libs.common.other.MainHandler;
import com.lzy.glide.config.LoaderConfig;
import com.lzy.glide.glideimpl.GlideConfigFactory;
import com.lzy.glide.glideimpl.GlideLoaderConfig;
import com.lzy.glide.glideimpl.GlideLoaderImpl;
import com.lzy.glide.glideimpl.transform.RotateTransformation;
import com.lzy.glide.glideimpl.transform.RoundedCornersTransformation;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * desc: {@link GlideLoaderImpl} 单元测试
 * time: 2018/8/2 下午3:34
 * author: yangbincai
 * since V 1.0
 */
public class GlideLoaderImplTest {


    private LoaderConfig mUrlConfig;
    private GlideLoaderImpl mGlideLoader;
    private ImageView mTarget;
    private View mTarget2;
    private Target<Drawable> mTarget3;
    private LoaderConfig mUriConfig;
    private LoaderConfig mFileConfig;

    @Before
    public void setup() {
        mGlideLoader = new GlideLoaderImpl();
        Context context = getInstrumentation().getContext();
        mUrlConfig = GlideConfigFactory.getInstance().obtain().load("this is test")
                .with(context)
                .placeholder(android.R.drawable.btn_star)
                .error(android.R.drawable.btn_star)
                .centerCrop()
                .circleCrop()
                .asGif()
                .override(200, 300)
                .cornerAngle(2)
                .skipLocalCache(true)
                .skipMemoryCache(true)
                .rotate(190)
                .transforms(new CenterCrop(), new RotateTransformation(180), new RoundedCornersTransformation(10, 0))
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        mUriConfig = GlideConfigFactory.getInstance().obtain().load(Uri.parse("this is test"))
                .with(context)
                .placeholder(android.R.drawable.btn_star)
                .error(android.R.drawable.btn_star)
                .centerCrop()
                .circleCrop()
                .asGif()
                .override(200, 300)
                .cornerAngle(2)
                .skipLocalCache(true)
                .skipMemoryCache(true)
                .rotate(190)
                .transforms(new CenterCrop(), new RotateTransformation(180), new RoundedCornersTransformation(10, 0))
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        mFileConfig = GlideConfigFactory.getInstance().obtain().load(new File("/mnt/sdcard/test.jpg"))
                .with(context)
                .placeholder(android.R.drawable.btn_star)
                .error(android.R.drawable.btn_star)
                .centerCrop()
                .circleCrop()
                .asGif()
                .override(200, 300)
                .cornerAngle(2)
                .skipLocalCache(true)
                .skipMemoryCache(true)
                .rotate(190)
                .transforms(new CenterCrop(), new RotateTransformation(180), new RoundedCornersTransformation(10, 0))
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        mTarget = new ImageView(context);
        mTarget2 = new View(context);
        //do nothing just for test
        mTarget3 = new SimpleTarget<Drawable>(){

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                //do nothing just for test
            }
        };
    }

    /**
     *  需要context环境 故该方法放在android test中
     */
    @Test
    public void loadImage() throws Exception{
        testUrlLoad(mUrlConfig);
        testUriLoad(mUriConfig);
        testFileLoad(mFileConfig);

    }

    private void testUrlLoad(LoaderConfig config) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        assertNotNull(config);

        assertEquals(config.getClass(), GlideLoaderConfig.class);

        //由于调用into方法会开启glide加载 故不调用into 手动设置target
        ((GlideLoaderConfig) config).targetDrawable = mTarget3;

        //反射调用private方法
        Class<? extends GlideLoaderImpl> clazz = mGlideLoader.getClass();

        RequestManager glideRequests = getGlideRequests(clazz);

        assertNotNull(glideRequests);

        RequestOptions requestOptions = getRequestOptions((GlideLoaderConfig) config, clazz);

        assertNotNull(requestOptions);

        //验证config配置的参数成功设置给了requestOptions
        verifyOptions((GlideLoaderConfig) config, requestOptions);

        //验证调用 需要主线程环境
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                glideRequests.load(((GlideLoaderConfig) config).url).apply(requestOptions).into(((GlideLoaderConfig) config).targetDrawable);
                assertTrue(1 > 0);
                //走到这说明调用glide加载ok
            }
        });
    }

    private void testUriLoad(LoaderConfig config) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        assertNotNull(config);

        assertEquals(config.getClass(), GlideLoaderConfig.class);

        //由于调用into方法会开启glide加载 故不调用into 手动设置target
        ((GlideLoaderConfig) config).targetImageView = mTarget;

        //反射调用private方法
        Class<? extends GlideLoaderImpl> clazz = mGlideLoader.getClass();

        RequestManager glideRequests = getGlideRequests(clazz);

        assertNotNull(glideRequests);

        RequestOptions requestOptions = getRequestOptions((GlideLoaderConfig) config, clazz);

        assertNotNull(requestOptions);

        //验证config配置的参数成功设置给了requestOptions
        verifyOptions((GlideLoaderConfig) config, requestOptions);

        //验证调用 需要主线程环境
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                glideRequests.load(((GlideLoaderConfig) config).uri).apply(requestOptions).into(((GlideLoaderConfig) config).targetImageView);
                assertTrue(1 > 0);
                //走到这说明调用glide加载ok
            }
        });
    }

    private void testFileLoad(LoaderConfig config) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        assertNotNull(config);

        assertEquals(config.getClass(), GlideLoaderConfig.class);

        //由于调用into方法会开启glide加载 故不调用into 手动设置target
        ((GlideLoaderConfig) config).targetView = mTarget2;

        //反射调用private方法
        Class<? extends GlideLoaderImpl> clazz = mGlideLoader.getClass();

        RequestManager glideRequests = getGlideRequests(clazz);

        assertNotNull(glideRequests);

        RequestOptions requestOptions = getRequestOptions((GlideLoaderConfig) config, clazz);

        assertNotNull(requestOptions);

        //验证config配置的参数成功设置给了requestOptions
        verifyOptions((GlideLoaderConfig) config, requestOptions);

        //验证调用 需要主线程环境
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                glideRequests.load(((GlideLoaderConfig) config).file).apply(requestOptions).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                    }
                });
                assertTrue(1 > 0);
                //走到这说明调用glide加载ok
            }
        });
    }

    private void verifyOptions(GlideLoaderConfig config, RequestOptions requestOptions) {
        assertEquals(requestOptions.getDiskCacheStrategy(), config.diskCacheStrategy);
        assertEquals(requestOptions.getErrorId(), config.errorResId);
        assertEquals(requestOptions.getPlaceholderId(), config.placeholderResId);
        assertEquals(requestOptions.getOverrideHeight(), config.targetHeight);
        assertEquals(requestOptions.getOverrideWidth(), config.targetWidth);
        assertEquals(requestOptions.isSkipMemoryCacheSet(), config.skipMemoryCache);
        assertEquals(requestOptions.isDiskCacheStrategySet(), config.skipLocalCache);
        Map<Class<?>, Transformation<?>> transformations = requestOptions.getTransformations();
        //CenterCrop circleCrop RotateTransformation RoundedCornersTransformation
        assertTrue(transformations.size() == 4);
    }

    private RequestOptions getRequestOptions(GlideLoaderConfig config, Class<? extends GlideLoaderImpl> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method getGlideRequestOptions = clazz.getDeclaredMethod("getGlideRequestOptions", GlideLoaderConfig.class);

        getGlideRequestOptions.setAccessible(true);

        return (RequestOptions) getGlideRequestOptions.invoke(mGlideLoader, config);
    }

    @NonNull
    private RequestManager getGlideRequests(Class<? extends GlideLoaderImpl> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method getGlideRequest = clazz.getDeclaredMethod("getGlideRequest", GlideLoaderConfig.class);

        getGlideRequest.setAccessible(true);

        return (RequestManager) getGlideRequest.invoke(mGlideLoader, (GlideLoaderConfig) mUrlConfig);
    }

}