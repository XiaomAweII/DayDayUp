package com.maxiaowei.mutithread.basic.create;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;

/**
 * @author xiaoweii
 * @create 2025-07-27 18:14
 * <p>
 * 线程创建方法二: 通过实现 Runnable 接口创建线程类
 */
public class CreateDemo2 {
    private static final int MAX_TURN = 5;

    // 线程的编号
    static int threadNo = 1;

    static class RunTarget implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.cfo(ThreadUtil.getCurThreadName() + " 运行第 " + (i + 1) + " 次");
            }
            Print.cfo(ThreadUtil.getCurThreadName() + " 线程运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread = null;
        // 方法 2: 使用 Runnable 实现类创建和启动线程
        for (int i = 0; i < 2; i++) {
            RunTarget target = new RunTarget();
            thread = new Thread(target, "RunnableThread-" + threadNo++);
            thread.start();
        }
        Print.cfo(ThreadUtil.getCurThreadName() + " 线程运行结束.");
    }
}
