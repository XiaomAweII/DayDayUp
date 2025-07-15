package com.maxiaowei.learnjuc.demo02;

/**
 * 功能描述: 线程的创建和启动
 * <p>
 * 作者: maxiaowei
 */
public class NewThread_2 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 运行线程t2");
            }
        };
        Thread thread = new Thread(runnable, "t2");
        // 输出结果 ： t2 运行线程t2
        thread.start();
    }
}
