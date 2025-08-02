package com.maxiaowei.common.helper;

import com.maxiaowei.common.entities.thread.ResizeLinkedBlockingQueue;
import com.maxiaowei.common.entities.thread.ThreadPoolChange;
import com.maxiaowei.common.entities.thread.ThreadPoolInfo;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 线程池管理器
 * <p>
 * 如何实现管理？
 * 1. 定义controller处理
 * <p>
 * GetMapping("获取所有的线程池信息") -> 通过 ThreadPoolManager.threadPoolInfoList() 查看
 * <p>
 * PostMapping("线程池扩缩容") -> 请求参数为 ThreadPoolChange -> 通过 ThreadPoolManager.changeThreadPool(ThreadPoolChange) 动态改变、管理线程池
 * <p>
 * 然后使用策略模式通过配置类 @Configuration(proxyBeanMethods = false) 进行线程池管理
 * <p>
 * 如下：
 * @Bean public ThreadPoolTaskExecutor emailThreadPool() {
 * return ThreadPoolManager.newThreadPool("emailThreadPool", 10, 20, 1000);
 * }
 * 开源动态线程池 dynamic-tp：https://github.com/dromara/dynamic-tp
 * 作者: maxiaowei
 */
public class ThreadPoolManager {
    private static Map<String, ThreadPoolTaskExecutor> threadPoolMap = new ConcurrentHashMap<String, ThreadPoolTaskExecutor>(16);

    // 核心线程数
    private static int corePoolSize = 1;

    // 最大线程数
    private static int maxPoolSize = Integer.MAX_VALUE;

    // 阻塞队列容量
    private static int queueCapacity = Integer.MAX_VALUE;

    // 线程存活周期
    private static int keepAliveSeconds = 60;

    /**
     * 创建新的线程池，如果线程池已经创建，返回已经创建的线程池，
     * <p>
     * 核心线程数{@link ThreadPoolManager#corePoolSize}，最大线程数{@link ThreadPoolManager#maxPoolSize}
     *
     * @param name 线程池名称
     * @return 线程池
     */
    public static ThreadPoolTaskExecutor newThreadPool(String name) {
        return newThreadPool(name, corePoolSize, maxPoolSize);
    }

    /**
     * 创建新的线程池，如果线程池已经创建，返回已经创建的线程池
     *
     * @param name         线程池名称
     * @param corePoolSize 核心线程数
     * @param maxPoolSize  最大线程数
     * @return
     */
    public static ThreadPoolTaskExecutor newThreadPool(String name, int corePoolSize, int maxPoolSize) {
        return newThreadPool(name, corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds, null, null);
    }

    /**
     * 创建新的线程池，如果线程池已经创建，返回已经创建的线程池
     *
     * @param name          线程池名称
     * @param corePoolSize  核心线程数
     * @param maxPoolSize   最大线程数
     * @param queueCapacity 队列大小
     * @return
     */
    public static ThreadPoolTaskExecutor newThreadPool(String name, int corePoolSize, int maxPoolSize, int queueCapacity) {
        return newThreadPool(name, corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds, null, null);
    }

    /**
     * 创建新的线程池，如果线程池已经创建，返回已经创建的线程池
     *
     * @param name                     线程池名称
     * @param corePoolSize             核心线程数
     * @param maxPoolSize              最大线程数
     * @param queueCapacity            队列大小
     * @param keepAliveSeconds         线程池存活时间（秒）
     * @param threadFactory            线程工厂
     * @param rejectedExecutionHandler 拒绝策略
     * @return
     */
    public static ThreadPoolTaskExecutor newThreadPool(String name, int corePoolSize, int maxPoolSize, int queueCapacity, int keepAliveSeconds, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        return threadPoolMap.computeIfAbsent(name, threadGroupName -> {
            ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor() {
                private boolean initialized = false;

                @Override
                protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
                    if (queueCapacity > 0) {
                        return new ResizeLinkedBlockingQueue<>(queueCapacity);
                    } else {
                        return new SynchronousQueue<>();
                    }
                }

                @Override
                public void setQueueCapacity(int queueCapacity) {
                    if (this.initialized && this.getThreadPoolExecutor() != null &&
                            this.getThreadPoolExecutor().getQueue() != null &&
                            this.getThreadPoolExecutor().getQueue() instanceof ResizeLinkedBlockingQueue) {
                        ((ResizeLinkedBlockingQueue) this.getThreadPoolExecutor().getQueue()).setCapacity(queueCapacity);
                    }
                    super.setQueueCapacity(queueCapacity);
                }

                @Override
                public void afterPropertiesSet() {
                    if (initialized) {
                        return;
                    }
                    super.afterPropertiesSet();
                    this.initialized = true;
                }
            };
            threadPoolExecutor.setCorePoolSize(corePoolSize);
            threadPoolExecutor.setMaxPoolSize(maxPoolSize);
            threadPoolExecutor.setQueueCapacity(queueCapacity);
            threadPoolExecutor.setKeepAliveSeconds(keepAliveSeconds);
            threadPoolExecutor.setThreadGroupName(name);
            threadPoolExecutor.setThreadNamePrefix(name + "-");
            if (threadFactory != null) {
                threadPoolExecutor.setThreadFactory(threadFactory);
            }
            if (rejectedExecutionHandler != null) {
                threadPoolExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
            }
            threadPoolExecutor.afterPropertiesSet();
            return threadPoolExecutor;
        });
    }

    /**
     * 获取所有线程池信息
     *
     * @return
     */
    public static List<ThreadPoolInfo> threadPoolInfoList() {
        return threadPoolMap
                .entrySet()
                .stream()
                .map(entry -> threadPoolInfo(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * 动态变更线程池（如：扩缩容、扩缩队列大小）
     *
     * @param threadPoolChange 变更线程池信息
     */
    public static void changeThreadPool(ThreadPoolChange threadPoolChange) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = threadPoolMap.get(threadPoolChange.getName());
        if (threadPoolTaskExecutor == null) {
            throw new IllegalArgumentException();
        }
        if (threadPoolChange.getCorePoolSize() > threadPoolChange.getMaxPoolSize()) {
            throw new IllegalArgumentException();
        }
        synchronized (ThreadPoolManager.class) {
            if (threadPoolChange.getMaxPoolSize() > threadPoolTaskExecutor.getCorePoolSize()) {
                threadPoolTaskExecutor.setMaxPoolSize(threadPoolChange.getMaxPoolSize());
                threadPoolTaskExecutor.setCorePoolSize(threadPoolChange.getCorePoolSize());
                threadPoolTaskExecutor.setQueueCapacity(threadPoolChange.getQueueCapacity());
            } else {
                threadPoolTaskExecutor.setCorePoolSize(threadPoolChange.getCorePoolSize());
                threadPoolTaskExecutor.setMaxPoolSize(threadPoolChange.getMaxPoolSize());
                threadPoolTaskExecutor.setQueueCapacity(threadPoolChange.getQueueCapacity());
            }
        }
    }

    /**
     * 获取所有线程池的信息
     *
     * @param name
     * @param threadPool
     * @return
     */
    private static ThreadPoolInfo threadPoolInfo(String name, ThreadPoolTaskExecutor threadPool) {
        ThreadPoolInfo threadPoolInfo = new ThreadPoolInfo();
        threadPoolInfo.setName(name);
        threadPoolInfo.setCorePoolSize(threadPool.getCorePoolSize());
        threadPoolInfo.setMaxPoolSize(threadPool.getMaxPoolSize());
        threadPoolInfo.setActiveCount(threadPool.getActiveCount());
        threadPoolInfo.setQueueCapacity(threadPool.getQueueCapacity());
        threadPoolInfo.setQueueSize(threadPool.getQueueSize());
        return threadPoolInfo;
    }
}
