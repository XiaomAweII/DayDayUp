package com.maxiaowei.common.webhandler;

import com.maxiaowei.common.BusinessRuntimeException;
import com.maxiaowei.common.Result;
import com.maxiaowei.common.constants.ErrorCode;
import com.maxiaowei.common.utils.ResultUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

/**
 * 全局异常处理
 * <p>
 * 作者: maxiaowei
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理业务异常
     *
     * @param e       业务异常
     * @param request 当前请求
     * @return
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    public Result handleBusinessException(BusinessRuntimeException e, HttpServletRequest request) {
        // TODO 此处只打印日志 具体结合业务场景根据异常处理数据
        log.error("request： {}, exception : {}", request.getRequestURI(), e.getMessage(), e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理SpringBoot参数校验异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e, HttpServletRequest request) {
        log.error("request： {}, exception : {}", request.getRequestURI(), e.getMessage(), e);
        return ResultUtil.error(e.getMessage());
    }

    /**
     * 处理其他异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error("request： {}, exception : {}", request.getRequestURI(), e.getMessage(), e);
        return ResultUtil.error(ErrorCode.SYS_ERROR, "系统异常，请联系管理员稍后重试！");
    }
}
