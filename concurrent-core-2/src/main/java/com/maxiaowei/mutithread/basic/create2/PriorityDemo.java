package com.maxiaowei.mutithread.basic.create2;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;

/**
 * 功能描述: 线程的优先级
 * <p>
 * 作者: maxiaowei
 */
public class PriorityDemo {
    public static final int SLEEP_GAP = 1000;

    static class PriotitySetThread extends Thread {
        static int threadNo = 1;

        public PriotitySetThread() {
            super("thread-" + threadNo);
            threadNo++;
        }

        public long opportunities = 0;

        @Override
        public void run() {
            for (int i = 0; ; i++) {
                opportunities++;
            }
        }
    }

    public static void main(String[] args) {
        PriotitySetThread[] threads = new PriotitySetThread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new PriotitySetThread();
            // 优先级的设置 1~10
            threads[i].setPriority(i + 1);
        }
        for (PriotitySetThread thread : threads) {
            thread.start();
        }
        ThreadUtil.sleepMilliSeconds(SLEEP_GAP); // 等待线程运行1秒
        for (PriotitySetThread thread : threads) {
            thread.stop(); // 停止线程
        }
        for (PriotitySetThread thread : threads) {
            Print.cfo(thread.getName() +
                    "-优先级为-" + thread.getPriority() + // 获取优先级
                    "-机会值为-" + thread.opportunities); // 机会值越大说明获取的时间片越多
        }
    }
}
