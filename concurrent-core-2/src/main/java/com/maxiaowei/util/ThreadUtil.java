package com.maxiaowei.util;

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

    public static void sleepMilliSeconds(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
