package com.jollycorp.android.libs.common.other;

import android.os.Looper;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * desc:{@link MainHandler} 单元测试  <br/>
 * time: 2018/8/1 上午10:20 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class MainHandlerTest {

    /**
     * {@link MainHandler#post(Runnable)} 单元测试
     */
    @Test
    public void post() {
        final Integer lock = 1;

        // 1, 当前线程非主线程
        assertNotEquals(Looper.getMainLooper().getThread(), Thread.currentThread());

        MainHandler.getInstance().post(() -> {
            // 2, 主线程执行
            assertEquals(Looper.getMainLooper().getThread(), Thread.currentThread());

            synchronized (lock) {
                lock.notify();
            }
        });

        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link MainHandler#postDelayed(Runnable, long)} 单元测试
     */
    @Test
    public void postDelayed() {
        final Integer lock = 1;
        final long delayMills = 1000;
        final long currentTime = System.currentTimeMillis();

        MainHandler.getInstance().postDelayed(() -> {
            // 1, 主线程执行
            assertEquals(Looper.getMainLooper().getThread(), Thread.currentThread());

            // 2, 延时时间
            assertEquals(true, ((System.currentTimeMillis() - currentTime) >= delayMills));
            synchronized (lock) {
                lock.notify();
            }
        }, delayMills);

        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link MainHandler#removeCallbacks(Runnable)} 单元测试
     */
    @Test
    public void removeCallbacks() {
        final Runnable delayJob = () -> {
            // 1, 主线程执行
            assertEquals(true, false);
        };

        MainHandler.getInstance().postDelayed(delayJob, 1000);

        try {
            MainHandler.getInstance().removeCallbacks(delayJob);
            TimeUnit.MILLISECONDS.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}