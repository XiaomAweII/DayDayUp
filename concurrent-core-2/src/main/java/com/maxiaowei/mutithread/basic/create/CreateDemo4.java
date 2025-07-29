package com.maxiaowei.mutithread.basic.create;

import com.maxiaowei.util.Print;

import static com.maxiaowei.util.ThreadUtil.getCurThreadName;
import static com.maxiaowei.util.ThreadUtil.sleepMilliSeconds;
import static com.maxiaowei.util.ThreadUtil.sleepSeconds;

import java.util.concurrent.*;

/**
 * 功能描述: 线程创建方法四：通过线程池创建线程
 * <p>
 * 作者: maxiaowei
 */
public class CreateDemo4 {
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100_000_000;

    // 创建一个包含三个线程的线程池
    private static ExecutorService pool = Executors.newFixedThreadPool(3);

    static class DemoThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.cfo(getCurThreadName() + " , 轮次: " + i);
                sleepMilliSeconds(10);
            }
        }
    }

    static class ReturnableTask implements Callable<Long> {
        // 返回并发执行的时间
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            Print.cfo(getCurThreadName() + " 线程开始运行.");
            for (int i = 0; i < MAX_TURN; i++) {
                Print.cfo(getCurThreadName() + " , 轮次： " + i);
                sleepMilliSeconds(10);
            }
            long usedTime = System.currentTimeMillis() - startTime;
            Print.cfo(getCurThreadName() + " 线程运行结束。");
            return usedTime;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 执行线程实例， 无返回
        pool.execute(new DemoThread());

        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < MAX_TURN; i++) {
                    Print.cfo(getCurThreadName() + " , 轮次： " + i);
                    sleepMilliSeconds(10);
                }
            }
        });

        // 提交Callable执行目标实例， 有返回
        Future<Long> future = pool.submit(new ReturnableTask());
        Long result = future.get();
        Print.cfo("异步任务的执行结果为： " + result);
    }

}
