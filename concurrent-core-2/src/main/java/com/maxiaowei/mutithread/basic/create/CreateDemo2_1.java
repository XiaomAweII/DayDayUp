package com.maxiaowei.mutithread.basic.create;

import com.maxiaowei.util.Print;

import static com.maxiaowei.util.ThreadUtil.getCurThreadName;

/**
 * @author xiaoweii
 * @create 2025-07-27 18:14
 * <p>
 * 线程创建方法二: 通过实现 Runnable 接口创建线程类
 */
public class CreateDemo2_1 {
    private static final int MAX_TURN = 5;

    // 线程的编号
    static int threadNo = 1;

    public static void main(String[] args) {
        Thread thread = null;
        // 使用Runnable的匿名类创建和启动线程
        for (int i = 0; i < 2; i++) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < MAX_TURN; j++) {
                        Print.cfo(getCurThreadName() + ", 轮次： " + threadNo);
                    }
                    Print.cfo(getCurThreadName() + " 运行结束.");
                }
            }, "RunnableThread" + threadNo++);
            thread.start();
        }
        Print.cfo(getCurThreadName() + " 运行结束.");
    }
}
