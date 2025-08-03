package com.maxiaowei.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * 微服务运行时父异常
 * <p>
 * 作者: maxiaowei
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CloudRuntimeException extends RuntimeException {
    /**
     * HTTP 状态码
     */
    private int httpStatus;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 参考
     */
    private List<String> references;

    public CloudRuntimeException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public CloudRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public CloudRuntimeException(int httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
