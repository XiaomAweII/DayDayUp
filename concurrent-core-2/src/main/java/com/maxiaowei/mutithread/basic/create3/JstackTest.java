package com.maxiaowei.mutithread.basic.create3;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class JstackTest {
    private static Executor executor = Executors.newFixedThreadPool(5);

    private static final Object lock = new Object();

    public static void main(String[] args) {
        MyRunnableImpl myRunnable = new MyRunnableImpl();
        MyRunnableImpl myRunnable1 = new MyRunnableImpl();
        executor.execute(myRunnable);
        executor.execute(myRunnable1);

    }

    static class MyRunnableImpl implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                //死循环
                calculate();
            }
        }
        private void calculate(){
            int i = 0;
            while (true){
                i++;
            }
        }
    }
}
