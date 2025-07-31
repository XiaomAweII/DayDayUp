package com.maxiaowei.mutithread.basic.use;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class YieldDemo {
    public static final int MAX_TURN = 100; //执行次数
    public static AtomicInteger index = new AtomicInteger(0); //执行编号

    //记录线程的执行次数
    private static Map<String, AtomicInteger> metric = new HashMap<>();

    // 输出线程的执行次数
    private static void pringMetric() {
        Print.tco("metric = " + metric);
    }

    static class YieldThread extends Thread {
        static int threadSeqNumber = 1;

        public YieldThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
            // 将线程加入到执行次数统计map
            metric.put(this.getName(), new AtomicInteger(0));
        }

        public void run() {
            for (int i = 0; i < MAX_TURN && index.get() < MAX_TURN; i++) {
                Print.tco("线程优先级：" + getPriority());
                index.incrementAndGet();
                // 统计一次
                metric.get(this.getName()).incrementAndGet();
                if (i % 2 == 0) {
                    // 让步: 让出执行的权限
                    Thread.yield();
                }
            }
            // 输出所有线程的执行次数
            pringMetric();
            Print.tco(getName() + " 运行结束.");
        }
    }

    @Test
    public void test() {
        Thread thread1 = new YieldThread();
        // 设置为最高的优先级
        thread1.setPriority(Thread.MAX_PRIORITY);
        Thread thread2 = new YieldThread();
        // 设置为最低的优先级
        thread2.setPriority(Thread.MIN_PRIORITY);
        Print.tco("启动线程.");
        thread1.start();
        thread2.start();
        ThreadUtil.sleepSeconds(100);
    }
}
