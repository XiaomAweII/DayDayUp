package com.maxiaowei.common.entities.thread;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 线程池扩缩容请求参数
 * <p>
 * 作者: maxiaowei
 */
@Getter
@Setter
@NoArgsConstructor
public class ThreadPoolChange {
    //线程池名称
    private String name;
    //核心线程数
    private int corePoolSize;
    //最大线程数
    private int maxPoolSize;
    //队列容量
    private int queueCapacity;
}
