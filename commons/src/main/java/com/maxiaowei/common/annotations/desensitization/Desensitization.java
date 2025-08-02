package com.maxiaowei.common.annotations.desensitization;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据脱敏注解
 * 配置脱敏策略实现不同脱敏
 * <p>
 * 如何使用? 在成员属性上打上注解并配置脱敏策略(可自定义脱敏策略)
 * <p>
 * 作者: maxiaowei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationJsonSerializable.class)
public @interface Desensitization {
    /**
     * 脱敏策略
     */
    DesensitizationStrategy value();
}
