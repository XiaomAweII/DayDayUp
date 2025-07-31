package com.maxiaowei.mutithread.basic.use;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;

/**
 * 功能描述: 线程名称的设置和获取
 * <p>
 * 作者: maxiaowei
 */
public class ThreadNameDemo {
    private static final int MAX_TURN = 3;

    // 异步执行目标类
    static class RunTarget implements Runnable {// 实现 Runnable 接口

        public void run() { // 重写 run() 方法
            for (int turn = 0; turn < MAX_TURN; turn++) {
                ThreadUtil.sleepMilliSeconds(500); // 线程睡眠
                Print.tco("线程执行轮次" + turn);
            }
        }
    }

    public static void main(String[] args) {
        RunTarget target = new RunTarget();                    // 实例化 Runnable 异步执行目标类
        new Thread(target).start();                            // 系统自动设置线程名称
        new Thread(target).start();                            // 系统自动命令线程名称
        new Thread(target).start();
        new Thread(target, "手动命名线程-A").start();    // 手动设置线程名称
        new Thread(target, "手动命名线程-B").start();
        ThreadUtil.sleepSeconds(Integer.MAX_VALUE);            // 主线程不结束

    }
}
