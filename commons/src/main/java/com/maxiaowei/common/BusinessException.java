package com.maxiaowei.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 定义通用业务异常
 * <p>
 * 作者: maxiaowei
 */
@Setter
@Getter
public class BusinessException extends RuntimeException {
    private String code;

    /**
     * @param code    错误编码
     * @param message 错误提示
     */
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param code    错误编码
     * @param message 错误提示
     * @param cause   具体报错
     */
    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
