package com.maxiaowei.common.aspects;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class ReadWriteDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //从ThreadLocal中获取路由的策略
        return ReadWriteRoutingStrategyHolder.getReadWriteRoutingStrategy();
    }
}
