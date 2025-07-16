package com.maxiaowei.learnjuc.base.demo09.communication;

/**
 * 功能描述: volatile保证可见性实现线程间通信
 * <p>
 * 作者: maxiaowei
 */
public class VolatileDemo {
    private static volatile boolean stop = false;
//    private static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程t1开始执行");
                int i = 0;
                while (!stop) {
                    i++;
                }
                System.out.println("跳出循环");
            }
        }, "t1");
        t1.start();

        Thread.sleep(1000);
        stop = true;
        System.out.println("主线程修改stop=true");
    }
}
