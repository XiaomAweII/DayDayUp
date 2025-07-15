package com.maxiaowei.learnjuc.base.demo03;

import com.maxiaowei.learnjuc.base.utils.SleepTools;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述: join的测试案例
 * <p>
 * 作者: maxiaowei
 */
@Slf4j
public class ThreadJoinDemo {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        log.debug("{} 开始执行", Thread.currentThread().getName());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("{} 开始执行", Thread.currentThread().getName());
                SleepTools.second(1);
                count = 5;
                log.debug("{} 执行完成", Thread.currentThread().getName());
            }
        }, "t1");
        t1.start();

        // 等待 t1 线程执行结束
        t1.join(500);
        log.debug("结果为：{}", count);
        log.debug("执行完成");


    }
}
