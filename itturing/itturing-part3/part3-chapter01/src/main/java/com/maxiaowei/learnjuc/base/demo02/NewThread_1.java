package com.maxiaowei.learnjuc.base.demo02;

/**
 * 功能描述: 线程的创建和启动
 * <p>
 * 作者: maxiaowei
 */
public class NewThread_1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 运行线程t1");
            }
        }, "t1");

        // 启动线程 start() / run()
        // t1 运行线程t1
        t1.start();

        // main 运行线程t1
//        t1.run();
    }
}
