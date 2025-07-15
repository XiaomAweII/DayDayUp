package com.maxiaowei.learnjuc.demo01;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class OnlyMain {
    public static void main(String[] args) {
        // Java 虚拟机线程系统的管理接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        // 转储所有线程信息
        // 不需要获取同步的monitor和synchronizer信息，仅仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("【" + threadInfo.getThreadId() + "】"
                    + threadInfo.getThreadName());
        }
    }
}
