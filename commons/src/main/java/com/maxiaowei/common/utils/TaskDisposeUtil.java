package com.maxiaowei.common.utils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/**
 * 任务批处理通用工具类
 * <p>
 * 使用：
 * TaskDisposeUtils.dispose(taskList, Consumer<? super T> consumer, executorService);
 * <p>
 * 作者: maxiaowei
 */
public class TaskDisposeUtil {
    /**
     * 使用线程池批处理文件，当所有任务处理完毕后才会返回
     *
     * @param taskList 任务列表
     * @param consumer 处理任务的方法
     * @param executor 线程池
     * @param <T>      泛型
     * @throws InterruptedException 线程中断异常
     */
    public static <T> void dispose(List<T> taskList, Consumer<? super T> consumer, Executor executor) throws InterruptedException {
        if (taskList == null || taskList.isEmpty()) {
            return;
        }
        Objects.requireNonNull(consumer);

        CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
        for (T item : taskList) {
            executor.execute(() -> {
                try {
                    consumer.accept(item);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }
}
