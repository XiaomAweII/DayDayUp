package com.maxiaowei.common.utils;

import com.maxiaowei.common.BusinessRuntimeException;
import com.maxiaowei.common.IMsgEnum;

/**
 * 构建通用异常信息
 * <p>
 * 作者: maxiaowei
 */
public class BusinessExceptionUtil {
    public static BusinessRuntimeException getBusinessException(String code, String msg) {
        return new BusinessRuntimeException(code, msg);
    }

    public static BusinessRuntimeException getBusinessException(String code, String msg, Exception e) {
        return new BusinessRuntimeException(code, msg, e);
    }

    public static BusinessRuntimeException getBusinessException(String code, IMsgEnum msgEnum) {
        return new BusinessRuntimeException(code, msgEnum.getMessage());
    }

    public static BusinessRuntimeException getBusinessException(String code, IMsgEnum msgEnum, Throwable cause) {
        return new BusinessRuntimeException(code, msgEnum.getMessage(), cause);
    }
}
