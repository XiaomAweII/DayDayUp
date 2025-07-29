package com.maxiaowei.util;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class Print {
    public static void cfo(String str) {
        // 获取当前线程的堆栈信息
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // stackTrace[0] 是 getStackTrace 方法
        // stackTrace[1] 是 当前方法 cfo
        // stackTrace[2] 是 调用 cfo 方法的类和方法, 即调用者

        // 获取调用该方法的类名和方法名
        if (stackTrace.length > 2) {
            // 调用者
            StackTraceElement caller = stackTrace[2];
            // 全类名 String fullClassName = caller.getClassName();
            String className = caller.getClass().getSimpleName();
            String methodName = caller.getMethodName();
            String currentThread = Thread.currentThread().getName();
            str = String.format("[%s.%s() thread:%s]: %s", className, methodName, currentThread, str);
        }
        System.out.println(str);
    }
}
