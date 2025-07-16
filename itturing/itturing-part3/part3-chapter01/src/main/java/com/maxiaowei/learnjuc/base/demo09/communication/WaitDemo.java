package com.maxiaowei.learnjuc.base.demo09.communication;

/**
 * 功能描述: 等待通知机制例子
 * <p>
 * 作者: maxiaowei
 */
public class WaitDemo {
    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (locker) {
                        System.out.println("wait开始");
                        locker.wait();
                    }
                    System.out.println("wait结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            synchronized (locker) {
                System.out.println("notify开始");
                locker.notifyAll();
                System.out.println("notify结束");
            }
        });
        t2.start();
    }
}
