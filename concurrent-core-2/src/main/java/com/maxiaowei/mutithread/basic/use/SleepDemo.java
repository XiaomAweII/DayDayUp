package com.maxiaowei.mutithread.basic.use;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class SleepDemo {
    public static final int SLEEP_GAP = 5000; // 睡眠时长 5 秒
    public static final int MAX_TURN = 50;    // 睡眠次数, 稍微多点方便使用Jstack

    static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread() {
            super("slleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.tco(ThreadUtil.getName() + ", 睡眠轮次: " + i);
                // 线程睡眠一会
                ThreadUtil.sleepMilliSeconds(SLEEP_GAP);
            }
            Print.tco(ThreadUtil.getName() + " 运行结束.");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new SleepThread();
            thread.start();
        }
        Print.tco(ThreadUtil.getCurThreadName() + " 运行结束.");
    }
}
