package com.maxiaowei.util;

import java.util.concurrent.locks.LockSupport;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class ThreadUtil {
    /**
     * 获取当前线程名
     * <p>
     * 为什么要通过工具类获取当前线程名?
     * 原因是如果自己实现 Runnable 接口 时, 定义的内部类和 Thread 不再是继承关系, 无法直接调用 Thread 类的任何实例方法
     *
     * @return 当前线程名
     */
    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    public static Thread.State getCurThreadState() {
        return Thread.currentThread().getState();
    }

    public static String getName() {
        return getCurThreadName();
    }

    public static Thread.State getState() {
        return getCurThreadState();
    }

    public static void sleepMilliSeconds(int milliSeconds) {
        LockSupport.parkNanos(milliSeconds * 1_000_000L);
    }

    public static void sleepSeconds(int seconds) {
        sleepMilliSeconds(seconds * 1_000);
    }
}
