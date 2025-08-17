package com.maxiaowei.mutithread.basic.create3;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class CreateThreadPoolDemo {
    public static final int SLEEP_GAP = 500;

    //异步任务的执行目标类
    static class TargetTask implements Runnable {
        static AtomicInteger taskNo = new AtomicInteger(1);
        private String taskName;

        public TargetTask() {
            taskName = "task-" + taskNo.get();
        }

        public void run() {
            Print.tco("任务： " + taskName + " doing");
            // 线程睡眠一会
            ThreadUtil.sleepMilliSeconds(SLEEP_GAP);
            Print.tco(taskName + " 运行结束");
        }
    }

    @Test
    public void testSingleThreadExecutor() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }
        ThreadUtil.sleepSeconds(1000);
        // 关闭线程池
        pool.shutdown();
    }
}
