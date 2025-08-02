package com.maxiaowei.common.aspects;

import com.maxiaowei.common.annotations.readwrite.ReadWriteRoutingStrategy;

import java.util.function.Supplier;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class ReadWriteRoutingStrategyHolder {
    private static ThreadLocal<ReadWriteRoutingStrategy> readWriteRoutingStrategyThreadLocal = new ThreadLocal<>();

    public static void setReadWriteRoutingStrategy(ReadWriteRoutingStrategy readWriteRoutingStrategy) {
        readWriteRoutingStrategyThreadLocal.set(readWriteRoutingStrategy);
    }

    /**
     * 路由到主库
     */
    public static void master() {
        setReadWriteRoutingStrategy(ReadWriteRoutingStrategy.MASTER);
    }

    /**
     * 路由到从库
     */
    public static void slave() {
        setReadWriteRoutingStrategy(ReadWriteRoutingStrategy.SLAVE);
    }

    /**
     * 强制走主库执行 execute的代码
     *
     * @param execute
     * @param <T>
     * @return
     */
    public static <T> T hitMaster(Supplier<T> execute) {
        ReadWriteRoutingStrategy old = getReadWriteRoutingStrategy();
        try {
            setReadWriteRoutingStrategy(ReadWriteRoutingStrategy.HIT_MASTER);
            return execute.get();
        } finally {
            readWriteRoutingStrategyThreadLocal.set(old);
        }
    }

    /**
     * 获取读写策略
     *
     * @return
     */
    public static ReadWriteRoutingStrategy getReadWriteRoutingStrategy() {
        return readWriteRoutingStrategyThreadLocal.get();
    }
}
