package com.maxiaowei.common.utils;

import com.maxiaowei.common.PageResult;
import com.maxiaowei.common.Result;

/**
 * 构建常见返回结果
 * <p>
 * 作者: maxiaowei
 */
public class ResultUtil {
    public static <T> Result<T> success() {
        return new Result<>(true, null, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, null);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(true, null, message);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(true, data, message);
    }

    public static <T> Result<T> success(T data, String message, String code) {
        return new Result<>(true, data, message, code);
    }

    public static <T> PageResult<T> success(T data, long current, long code, long page) {
        return new PageResult<>(true, data, current, code, page);
    }

    public static <T> Result<T> error() {
        return new Result<>(false, null, null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(false, null, message);
    }

    public static <T> Result<T> error(String code, String message) {
        return new Result<>(false, null, code, message);
    }

    public static <T> PageResult<T> error(String code, String message, long current, long size) {
        PageResult<T> pageResult = new PageResult<>(false, null, current, size, 0);
        pageResult.setCode(code);
        pageResult.setMsg(message);
        return pageResult;
    }
}
