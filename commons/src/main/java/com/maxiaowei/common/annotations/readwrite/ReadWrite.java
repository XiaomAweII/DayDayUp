package com.maxiaowei.common.annotations.readwrite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 读写分离注解
 * <p>
 * 作者: maxiaowei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadWrite {
    // 获取路由策略（主库、从库、还是强制路由到主库？）
    ReadWriteRoutingStrategy value();
}
