package com.xihu.conference.xihu.utils;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

public class ThreadLocalUtils {
    //提供ThreadLocal对象，
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    //根据键获取值
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    //存储键值对
    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    //清除ThreadLocaL 防止内存泄漏
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}