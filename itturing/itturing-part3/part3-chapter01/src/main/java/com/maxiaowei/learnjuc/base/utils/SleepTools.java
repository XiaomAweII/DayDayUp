package com.maxiaowei.learnjuc.base.utils;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 线程休眠辅助工具类
 * <p>
 * 作者: maxiaowei
 */
public class SleepTools {
    /**
     * 按秒休眠
     *
     * @param seconds 秒数
     */
    public static void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按毫秒数休眠
     *
     * @param milliseconds 毫秒数
     */
    public static void ms(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
