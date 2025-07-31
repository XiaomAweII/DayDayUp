package com.maxiaowei.mutithread.basic.use;

import com.maxiaowei.util.Print;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class JoinDemo {
    public static final int SLEEP_GAP = 5000; //睡眠时长
    public static final int MAX_TURN = 50; //睡眠次数

    static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        public void run() {
            try {
                Print.tco(getName() + " 进入睡眠.");
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Print.tco(getName() + " 发生被异常打断.");
                return;
            }
            Print.tco(getName() + " 运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new SleepThread();
        Print.tco("启动thread1.");
        thread1.start();
        try {
            thread1.join(); // 合并线程1, 不限时
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Print.tco("启动thread2.");
        // 启动第二条线程, 并且进行限时合并,等待时间为1秒
        SleepThread thread2 = new SleepThread();
        thread2.start();
        try {
            thread2.join(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Print.tco("线程运行结束.");
    }
}
