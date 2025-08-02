package com.maxiaowei.common.annotations.readwrite;

/**
 * 读写分离路由策略
 * <p>
 * 作者: maxiaowei
 */
public enum ReadWriteRoutingStrategy {
    MASTER, //路由到主库
    SLAVE, //路由到从库
    HIT_MASTER //强制路由到主库
}
