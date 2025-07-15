package com.maxiaowei.learnjuc.base.demo02;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 功能描述: 线程的创建和启动
 * <p>
 * 作者: maxiaowei
 */
@Slf4j
public class NewThread_3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务对象
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("{} 运行线程task", Thread.currentThread().getName());
            return 100;
        });

        // 参数1 是任务对象; 参数2 是线程名字，推荐
        new Thread(task, "t3").start();

        // 主线程阻塞，同步等待 task 执行完毕的结果
        Integer result = task.get();
        log.debug("{} 线程等待结果为: {}", Thread.currentThread().getName(), result);
    }
}
