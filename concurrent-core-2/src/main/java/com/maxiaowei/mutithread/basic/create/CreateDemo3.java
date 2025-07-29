package com.maxiaowei.mutithread.basic.create;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 功能描述: 线程创建方法三 : 使用 Callable 和 FutureTask 创建线程
 * <p>
 * 作者: maxiaowei
 */
public class CreateDemo3 {
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100_000_000;

    // 1. 创建一个Callable接口的实现类
    static class ReturnableTask implements Callable<Long> {
        // 2. 编写好异步执行的具体逻辑, 可以有返回值
        public Long call() throws Exception {
            Long startTime = System.currentTimeMillis();
            Print.cfo(ThreadUtil.getCurThreadName() + " 线程运行开始.");
            ThreadUtil.sleepMilliSeconds(1000);
            for (int i = 0; i < COMPUTE_TIMES; i++) {
                int j = i * 10_000;
            }
            Long usedTime = System.currentTimeMillis() - startTime;
            Print.cfo(ThreadUtil.getCurThreadName() + " 线程运行结束.");
            return usedTime;
        }
    }

    public static void main(String[] args) {
        // 3. 实例化
        ReturnableTask task = new ReturnableTask();
        // 4.
        FutureTask<Long> futureTask = new FutureTask<>(task);
        // 5.
        Thread thread = new Thread(futureTask, "returnableThread");
        // 6.
        thread.start();
        ThreadUtil.sleepMilliSeconds(500);
        Print.cfo(ThreadUtil.getCurThreadName() + "  让子弹飞一会儿.");
        Print.cfo(ThreadUtil.getCurThreadName() + "  做一点自己的事情.");
        for (int i = 0; i < COMPUTE_TIMES / 2; i++) {
            int j = i * 10_000;
        }
        Print.cfo(ThreadUtil.getCurThreadName() + "  获取并发任务的执行结果.");

        try {
            // 7.
            Print.cfo(ThreadUtil.getCurThreadName() + " 线程占用时间: " + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        Print.cfo(ThreadUtil.getCurThreadName() + " 运行结束.");

    }
}
