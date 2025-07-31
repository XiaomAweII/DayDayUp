package com.maxiaowei.mutithread.basic.use;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class DaemonDemo {
    public static final int SLEEP_GAP = 500; //每一轮的睡眠时长
    public static final int MAX_TURN = 4; //用户线程执行轮次

    // 守护线程实现类
    static class DaemonThread extends Thread {
        public DaemonThread() {
            super("daemonThread");
        }

        public void run() {

        }
    }
}
