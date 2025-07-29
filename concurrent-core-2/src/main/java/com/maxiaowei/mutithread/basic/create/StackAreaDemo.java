package com.maxiaowei.mutithread.basic.create;

import com.maxiaowei.util.Print;

/**
 * @author xiaoweii
 * @create 2025-07-27 17:15
 * <p>
 * 演示 Java 程序的线程信息
 */
public class StackAreaDemo {
    public static void main(String[] args) {
        Print.cfo("当前线程名称: " + Thread.currentThread().getName());
        Print.cfo("当前线程 ID: " + Thread.currentThread().getId());
        Print.cfo("当前线程状态: " + Thread.currentThread().getState());
        Print.cfo("当前线程优先级: " + Thread.currentThread().getPriority());
        Print.cfo("当前线程是否为守护线程: " + Thread.currentThread().isDaemon());
        int a = 1, b = 10;
        int c = a / b;
        anotherFun1();
    }

    private static void anotherFun1() {
        int a = 1, b = 10;
        int c = a / b;
        anotherFun2();
    }

    private static void anotherFun2() {
        int a = 1, b = 10;
        int c = a / b;
    }
}
