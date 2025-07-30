package com.maxiaowei.mutithread.basic.create3;

import com.maxiaowei.util.Print;

import java.util.ArrayList;
import java.util.List;

import static com.maxiaowei.util.ThreadUtil.sleepMilliSeconds;

/**
 * 功能描述:
 * <p>
 * <p>
 * 作者: maxiaowei
 * public static enum State {
 * NEW,              // 新建
 * RUNNABLE,         // 可执行: 包括操作系统的就绪, 运行两种状态
 * BLOCKED,          // 阻塞
 * WAITING,          // 等待
 * TIMED_WAITING,    // 计时等待
 * TERMINATED        // 终止
 * }
 */
public class StatusDemo {
    // 每个线程执行的轮次
    public static final long MAX_TURN = 5;

    // 线程编号
    static int threadSepNumber = 0;

    // 全局的静态线程列表
    static List<Thread> threadList = new ArrayList<>();

    // 输出静态线程列表中, 每个线程的状态
    private static void printThreadStatus() {
        for (Thread thread : threadList) {
            Print.cfo(thread.getName() + " 状态为 " + thread.getState());
        }
    }

    // 向全局的静态线程列表加入线程
    private static void addStatusThread(Thread thread) {
        threadList.add(thread);
    }

    static class StatusDemoThread extends Thread {
        public StatusDemoThread() {
            super("statusPrintThread" + (++threadSepNumber));
            // 将自己加入到全局的静态线程列表中
            threadList.add(this);
        }

        public void run() {
            Print.cfo(getName() + " 状态为 " + getState());
            for (long i = 0; i < MAX_TURN; i++) {
                // 线程睡眠
                sleepMilliSeconds(500);
                // 输出所有线程的状态
                printThreadStatus();
            }
            Print.cfo(getName() + " - 运行结束.");
        }
    }

    public static void main(String[] args) {
        // 将main线程加入全局列表
        addStatusThread(Thread.currentThread());
        // 新建三个线程, 这些线程在构造器中会将自己加入全局列表
        Thread sThread1 = new StatusDemoThread();
        Print.cfo(sThread1.getName() + "- 状态为 " + sThread1.getState());
        Thread sThread2 = new StatusDemoThread();
        Print.cfo(sThread2.getName() + "- 状态为 " + sThread2.getState());
        Thread sThread3 = new StatusDemoThread();
        Print.cfo(sThread3.getName() + "- 状态为 " + sThread3.getState());

        sThread1.start();

        sleepMilliSeconds(500);
        sThread2.start();

        sleepMilliSeconds(500);
        sThread3.start();
    }
}
