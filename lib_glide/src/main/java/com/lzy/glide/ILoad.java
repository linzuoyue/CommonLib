package com.lzy.glide;

import com.lzy.glide.config.LoaderConfig;
import com.lzy.glide.strategy.ICacheStrategy;
import com.lzy.glide.strategy.ILoadStrategy;
import com.lzy.glide.strategy.IRequestStrategy;

/**
 * desc: 封装三方图片框架的实现类，正在调用三方图片框架去加载，除了ICacheStrategy, ILoadStrategy <br/>
 * time: 2018-7-13 <br/>
 * author: 杨斌才 <br/>
 * since: V 1.0 <br/>
 */
public interface ILoad<T extends LoaderConfig> extends ICacheStrategy, ILoadStrategy<T>, IRequestStrategy {

}
