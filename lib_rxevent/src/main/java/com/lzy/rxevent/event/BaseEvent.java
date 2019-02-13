package com.lzy.rxevent.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * desc: 事件基类 <br/>
 * author: 林佐跃 <br/>
 * date: 2018/8/15 <br/>
 * since V mello 1.0.0 <br/>
 */
public class BaseEvent<T,ST,E>{

    /**
     * 类型
     */
    @NonNull
    private T type;

    /**
     * 子类型
     */
    @Nullable
    private ST subType;

    /**
     * 额外
     */
    @Nullable
    private E extra;

    public BaseEvent(@NonNull T type) {
        this(type, null);

    }

    public BaseEvent(T type, @Nullable ST subType) {
        this(type, subType, null);
    }

    public BaseEvent(@NonNull T type, @Nullable ST subType, @Nullable E extra) {
        this.type = type;
        this.subType = subType;
        this.extra = extra;
    }

    @NonNull
    public T getType() {
        return type;
    }

    public void setType(@NonNull T type) {
        this.type = type;
    }

    @Nullable
    public ST getSubType() {
        return subType;
    }

    public void setSubType(@Nullable ST subType) {
        this.subType = subType;
    }

    @Nullable
    public E getExtra() {
        return extra;
    }

    public void setExtra(@Nullable E extra) {
        this.extra = extra;
    }



}
