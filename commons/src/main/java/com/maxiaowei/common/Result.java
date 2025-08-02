package com.maxiaowei.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 定义通用返回值
 * <p>
 * 作者: maxiaowei
 */
@Setter
@Getter
@NoArgsConstructor
public class Result<T> {
    /**
     * 请求处理是否成功
     */
    private boolean success;

    /**
     * 定义泛型返回数据
     * 用于返回给前端业务数据
     */
    public T data;

    /**
     * 提示信息，返回业务处理情况
     * 如请求处理success失败(false)的提示信息
     */
    private String msg;

    /**
     * 错误编码
     * 返回常见错误编码，便于问题定位以及状态查看
     */
    private String code;

    public Result(boolean success, T data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public Result(boolean success, T data, String code, String msg) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

}
