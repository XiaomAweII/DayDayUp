package com.maxiaowei.common.entities.thread;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 线程池信息
 * <p>
 * 作者: maxiaowei
 */
@Getter
@Setter
@NoArgsConstructor
public class ThreadPoolInfo {
    private String name;
    //核心线程数
    private int corePoolSize;
    //最大线程数
    private int maxPoolSize;
    //活动的线程数
    private int activeCount;
    //队列的容量
    private int queueCapacity;
    //队列中当前任务数量
    private int queueSize;
}
