package com.maxiaowei.mutithread.basic.create;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;

/**
 * @author xiaoweii
 * @create 2025-07-27 18:14
 * <p>
 * 线程创建方法一: 继承 Thread 类创建线程类
 */
public class CreateDemo {
    private static final int MAX_TURN = 5;

    // 线程的编号
    static int threadNo = 1;

    /**
     * 为什么要将 DemoThread 定义为静态类？
     * 主要是为了访问外部类的成员属性和方法, 和线程的使用没有关系.
     */
    static class DemoThread extends Thread {
        public DemoThread() {
            super("DemoThread-" + threadNo++);
        }

        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.cfo(ThreadUtil.getCurThreadName() + " 运行第 " + (i + 1) + " 次");
            }
            Print.cfo(ThreadUtil.getCurThreadName() + " 线程运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread = null;
        // 方法 1: 使用 Thread 子类创建和启动线程
        for (int i = 0; i < 2; i++) {
            thread = new DemoThread();
            thread.start();
        }
        Print.cfo(ThreadUtil.getCurThreadName() + " 线程运行结束.");
    }
}
