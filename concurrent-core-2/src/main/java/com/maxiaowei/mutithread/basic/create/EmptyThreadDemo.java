package com.maxiaowei.mutithread.basic.create;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;

/**
 * @author xiaoweii
 * @create 2025-07-27 18:04
 * <p>
 * 如何创建一个空线程
 */
public class EmptyThreadDemo {
    public static void main(String[] args) {
        // 使用 Thread 类创建和启动线程
        Thread thread = new Thread();
        Print.cfo("线程名称: " + thread.getName());
        Print.cfo("线程 ID: " + thread.getId());
        Print.cfo("线程状态: " + thread.getState());
        Print.cfo("线程优先级: " + thread.getPriority());
        Print.cfo("线程是否为守护线程: " + thread.isDaemon());
        Print.cfo(ThreadUtil.getCurThreadName() + " 线程运行结束.");
        thread.start();
    }

}