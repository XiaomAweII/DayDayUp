package com.maxiaowei.learnjuc.base.demo03;

import com.maxiaowei.learnjuc.base.utils.SleepTools;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述: join的测试案例
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 * <p>
 * 作者: maxiaowei
 */
@Slf4j
public class ThreadJoinDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> log.debug("线程 {} 执行完成", Thread.currentThread().getName()), "t1");
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("线程 {} 执行完成", Thread.currentThread().getName());
        }, "t2");
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("线程 {} 执行完成", Thread.currentThread().getName());
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
